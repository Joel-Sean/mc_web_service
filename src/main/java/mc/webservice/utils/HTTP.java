package mc.webservice.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HTTP {
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	public static String request(String url, String data, Option... options) throws IOException {
		Options options2 = getOptions(options);
		return toString(stream(url, new ByteArrayInputStream(data.getBytes(options2.encoding)), options2), options2.encoding);
	}
	
	public static String request(String url, InputStream data, Option... options) throws IOException {
		Options options2 = getOptions(options);
		return toString(stream(url, data, options2), options2.encoding);
	}	
	
	public static InputStream stream(String url, String data, Option... options) throws IOException {
		Options options2 = getOptions(options);
		return stream(url, new ByteArrayInputStream(data.getBytes(options2.encoding)), options2);
	}
	
	public static InputStream stream(String url, InputStream data, Option... options) throws IOException {
		Options options2 = getOptions(options);
		return stream(url, data, options2);
	}
	
	private static InputStream stream(String url, InputStream data, Options options) throws IOException {
	
		String protocol = url.substring(0, url.indexOf(":"));
		
		URLConnection conn = new URL(protocol, options.proxyHost, options.proxyPort, url).openConnection();
		if (data != null) {
			conn.setDoOutput(true);
		}
		if (data != null) {
			OutputStream output = null;
			byte[] buffer = new byte[1024];
			int read = 0;
			try {
				output = conn.getOutputStream();
				while ((read = data.read(buffer)) > 0) {
					output.write(buffer, 0, read);
				}
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
					}
				}
				if (data != null) {
					try {
						data.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return conn.getInputStream();
	}
	
	
	public static Option ProxyHost(String value) {
		return new Option(OptionType.PROXY_HOST, value);
	}
	
	public static Option ProxyPort(String value) {
		return new Option(OptionType.PROXY_PORT, value);
	}
	
	private static Options getOptions(Option[] optionsInput) {
		Options options = new Options();
		for (Option option : optionsInput) {
			if (option.key == OptionType.PROXY_HOST) {
				options.proxyHost = option.value;
			}
			if (option.key == OptionType.PROXY_PORT) {
				options.proxyPort = Integer.parseInt(option.value);
			}

			if (option.key == OptionType.ENCODING) {
				options.encoding = option.value;
			}
		}
		return options;
	}
	
	private static String toString(InputStream result, String encoding) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int nRead;
		byte[] buffer = new byte[16384];

		while ((nRead = result.read(buffer, 0, buffer.length)) != -1) {
			stream.write(buffer, 0, nRead);
		}
		stream.flush();
		return new String(stream.toByteArray(), encoding);
	}
	
	private static final class Option {
		
		private final OptionType key;
		private final String value;
		
		public Option(OptionType key, String value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private static enum OptionType {
		ENCODING,
		PROXY_HOST,
		PROXY_PORT
	}
	
	private static final class Options {
		private String proxyHost;
		private int proxyPort = -1;
		String encoding = DEFAULT_ENCODING;
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

}
