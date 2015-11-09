package mc.webservice.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;


public class TRNGRamdomGenerator implements RandomGenerator {
	private static final Logger logger = Logger.getLogger(TRNGRamdomGenerator.class);
	
	private static final int TIMEOUT = 2000;
	private static final String PROP_URL = "url";
	private static String url;
	
	static {
		try {
			Properties props = CommonUtil.getPropInClassPath("trng.properties");
			url = props.getProperty(PROP_URL);
		}
		catch (Exception e) {
			logger.error("Failed to load trng properties file.");
			url = null;
		}
		logger.info("trng url configured: " + url);
	}

	@Override
	public int nextInt() throws Exception {
		String str = queryServer(url);
		return Integer.parseInt(str);
	}

	@Override
	public int nextInt(int n) throws Exception {
		String str = queryServer(url);
		int r = Integer.parseInt(str);
		return r % n;
	}
	
	private String queryServer(String url) throws Exception {
		KeyValue kv = query(url);
		validateResponse(kv, url);
		return getResult(kv);
	}
	
	private String getResult(KeyValue kv) {
		if (kv == null)
			return null;
		
		String result = kv.getValue();
		String[] r = result.split("\\|");
		if (r.length < 3) {
			throw new RuntimeException("Wrong returned format:" + result);
		}
		return r[2].trim();
	}
	
	private KeyValue query(String url) throws Exception {
		HttpClient hc=null;
		HttpGet hg;
		HttpResponse hr;

		int status=0;
		String result;
		try {
			HttpParams my_httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(my_httpParams, TIMEOUT);
			HttpConnectionParams.setSoTimeout(my_httpParams, TIMEOUT);			
			hc = new DefaultHttpClient(my_httpParams);
			hg = new HttpGet(url);
			hr = hc.execute(hg);
			
			if (hr != null)
			{
				status = hr.getStatusLine().getStatusCode();
				InputStream is = hr.getEntity().getContent();
				byte[] response = IOUtils.toByteArray(is);
				result = new String(response);
				return new KeyValue(status, result);
			}
						
		} finally {
			if (hc != null) {
				hc.getConnectionManager().shutdown();
			}
		}
		return null;
	}
	
	private void validateResponse(KeyValue kv, String url)
	{
		if (kv == null) {
			logger.error("null result returned from url:" + url);
			throw new RuntimeException("null result returned from url:" + url);
		}
		
		switch(kv.getKey()) {
			case 200:
				break;
			case 404:
				logger.error("url not found: " + url);
				throw new RuntimeException("url not found: " + url);
			case 500:
				logger.error("trng server error: " + kv.getValue() + " from url " + url);
				throw new RuntimeException("trng server error: " + kv.getValue() + " from url " + url);
			default:
				logger.error("unexpected server result " + kv + " from url " + url);
				throw new RuntimeException("unexpected server result " + kv + " from url " + url);
		}
	}

}
