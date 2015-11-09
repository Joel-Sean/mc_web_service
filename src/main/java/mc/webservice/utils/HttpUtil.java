package mc.webservice.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.log4j.Logger;

public class HttpUtil {
	
	private static final Logger logger = Logger.getLogger(HttpUtil.class);
	private static final String IPADDRESS_PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	private static Pattern pattern;
	
	static {
		pattern = Pattern.compile(IPADDRESS_PATTERN);		
	}
	
	public static String getIp(HttpServletRequest req) {
	    String ipAddress = req.getHeader("Incap-Client-IP");
        if (ipAddress == null || "".equals(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("true-client-ip");
        }
	    if (ipAddress == null || "".equals(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("x-forwarded-for");
        }
        if (ipAddress == null || "".equals(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("X_FORWARDED_FOR");
        }
        if (ipAddress == null || "".equals(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || "".equals(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("X-Real-IP");
        }
        if (ipAddress == null || "".equals(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || "".equals(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = req.getRemoteAddr();
        }
        
        String[] tempArray = ipAddress.split(",");
        for (int i = 0; i < tempArray.length; i++) {
            if (!"unknown".equalsIgnoreCase(tempArray[i])) {
                ipAddress = tempArray[i].replaceAll("\\s", "");
                break;
            }
        }
        return ipAddress;
	}
	
	public static boolean validateIp(String ip) {
		if (StringUtil.isEmpty(ip)) {
			return false;
		}
		
		return pattern.matcher(ip).matches();
	}
	
	public static String getBrowser(HttpServletRequest req) {
		String agent = req.getHeader("User-Agent");
		if (agent == null) {
			return "unknown";
		}
		agent = agent.toLowerCase();
		
		if (agent.indexOf("chrome") > -1) {
			if (agent.indexOf("metasr") > -1) {
				return "Sogou";
			} else if (agent.indexOf("opr") > -1 || agent.indexOf("opera") > -1) {
				return "Opera";
			} else {
				return "Chrome/Others";
			}
		} else if (agent.indexOf("opera") > -1) {
			return "Opera";
		} else if (agent.indexOf("firefox") > -1) {
			return "Firefox";
		} else if ((agent.indexOf("mozilla") > -1 && agent.indexOf("gecko")  > -1) || agent.indexOf("msie")  > -1) {
			return "IE";
		}else {
			return "unknown";
		}
	}
	
	public static String httpGet(HttpClient client, String agent, String cookie, String referer, String url, String encoding) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(url);
		request.getParams().setParameter(
		        ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
		if (cookie != null) {
			request.setHeader("Cookie", cookie);
		}
		request.setHeader("Referer", referer);
		if (agent != null) {
			request.setHeader("User-Agent", agent);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("URL: " + url);
			logger.debug("Cookie: " + cookie);
			logger.debug("Referer: " + referer);
			logger.debug("encoding: " + encoding);
		}
		HttpResponse response = client.execute(request);
		return encoding == null ? 
				IOUtils.toString(response.getEntity().getContent()) : 
					IOUtils.toString(response.getEntity().getContent(), encoding);
	}
	
	public static String httpGet(String referer, String url) throws ClientProtocolException, IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		HttpClient client = getHttpClient(null);
		try {
			return httpGet(client, null, null, referer, url, null);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
	}
	
	public static String httpGet(String proxy, String agent, String cookie, String referer, String url, String encoding) throws ClientProtocolException, IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		HttpClient client = getHttpClient(proxy);
		try {
			return httpGet(client, agent, cookie, referer, url, encoding);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
	}
	
	public static String httpPost(String referer, String url, String data) throws ClientProtocolException, IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		HttpClient client = getHttpClient(null);
		try {
			return httpPost(client, null, null, referer, url, data, null);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
	}
	
	public static String httpPost(String proxy, String agent, String cookie, String referer, String url, String data, String encoding) throws ClientProtocolException, IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		HttpClient client = getHttpClient(proxy);
		try {
			return httpPost(client, agent, cookie, referer, url, data, encoding);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
	}
	
	public static String httpPost(HttpClient client, String agent, String cookie, String referer, String url, String data, String encoding) throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost(url);
		request.getParams().setParameter(
		        ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
		// Override the default policy for this request
		if (cookie != null) {
			request.setHeader("Cookie", cookie);
		}
		request.setHeader("Referer", referer);
		if (agent != null) {
			request.setHeader("User-Agent", agent);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("URL: " + url);
			logger.debug("Cookie: " + cookie);
			logger.debug("Referer: " + referer);
			logger.debug("Date: " + data);
			logger.debug("encoding: " + encoding);
		}
		StringEntity entity = new StringEntity(data);
		entity.setContentType("application/x-www-form-urlencoded");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		return encoding == null ? 
				IOUtils.toString(response.getEntity().getContent()) : 
					IOUtils.toString(response.getEntity().getContent(), encoding);
	}
	
	public static HttpClient getHttpClient(String proxy) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException {
		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
		SchemeRegistry schemeRegistry = new SchemeRegistry();   
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
	    schemeRegistry.register(new Scheme("https", 443, 
			new SSLSocketFactory(
				new TrustStrategy() {
					@Override
					public boolean isTrusted(X509Certificate[] arg0, String arg1)
						throws CertificateException {
						return true;
					}
				},
				new X509HostnameVerifier() {
					@Override
					public boolean verify(String arg0, SSLSession arg1) {
						return true;
					}

					@Override
					public void verify(String arg0, SSLSocket arg1)
							throws IOException {
						return;
					}

					@Override
					public void verify(String arg0, X509Certificate arg1)
							throws SSLException {
						return;
					}

					@Override
					public void verify(String arg0, String[] arg1, String[] arg2)
							throws SSLException {
						// TODO Auto-generated method stub
						
					}
				}
			)
		));

		HttpClient client =new DefaultHttpClient(new SingleClientConnManager(schemeRegistry));
		if (proxy != null) {
			String[] tmp = proxy.split(":");
			String ip = tmp[0];
			int port = tmp.length == 1 ? 80: Integer.parseInt(tmp[1]);
			HttpHost httpProxy = new HttpHost(ip, port, "http");
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpProxy);
		}
		return client;
	}
	
	public static final String getDomain(HttpServletRequest request) {
		String host = request.getServerName();
		String domain = host;
		String[] temp = host.split("\\.");
		if (temp.length > 2) {
			int index = host.indexOf(".");
			if (index > 0) {
				domain = host.substring(index+1);
			}
		}
		return domain == null || domain.length() == 0 ? host : domain;
	}
	
	public static String httpRequest(String url, String agent, String cookie, String referer, String data, String encoding) throws IOException {
		URL urlRequest = new URL(url);
		URLConnection conn = urlRequest.openConnection();
		if (data != null) {
			conn.setDoOutput(true);
		}
//		System.out.println(agent + cookie + referer);
		conn.setRequestProperty("User-Agent", agent);
		if (agent != null) {
//			System.out.println(agent);
			conn.setRequestProperty("User-Agent", agent);
		}
		if (cookie != null) {
//			System.out.println(cookie);
			conn.setRequestProperty("Set-Cookie", cookie);
		}
		if (referer != null) {
//			System.out.println(referer);
			conn.setRequestProperty("referer", referer);
		}
//		Map<String, List<String>> headers = conn.getHeaderFields();
//		for (String key : headers.keySet()) {
//			System.out.print(key + "=");
//			for (String value : headers.get(key)) {
//				System.out.print(value + ",");
//			}
//			System.out.println();
//		}
		if (data != null) {
			OutputStream output = null;
			try {
			     output = conn.getOutputStream();
			     output.write(data.getBytes(encoding));
			} finally {
			     if (output != null) {
			    	 try { 
			    		 output.close(); 
			    	} catch (IOException logOrIgnore) {}
			     }
			}
		}
		
		return new String(StreamUtil.toByteArray(conn.getInputStream()), encoding);
		
	}
	
	public static void disableCertificateValidation() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}
			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}
		} };

		// Ignore differences between given hostname and certificate hostname
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
		}
	}

	public static Cookie findCookieByName (String name, HttpServletRequest req) {
		
		Cookie[] cookies = req.getCookies();
		if (cookies != null && name != null) {
			for (Cookie c : cookies) {
				if (name.equals(c.getName()))
					return c;
			}
		}
		
		return null;
	}
}
