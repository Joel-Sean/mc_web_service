package mc.webservice.utils;
public final class JspUtil {
	private JspUtil() {}
	
    public static String getHost(String url) {
        url = getDomainAndPort(url);
        int idx2 = url.indexOf(":");
        if (idx2 > -1) {
            url = url.substring(0, idx2);
        }        
        return url;
    }       
    
    public static String removePathInfo(String url) {
        url = getDomainAndPort(url);     
        return "http://" + url;
    }

	public static String getDomainAndPort(String url) {
		url = url.replaceAll("http://", "").trim();
        int idx = url.indexOf('/');
        if (idx > -1) {
            url = url.substring(0, idx);
        }
		return url;
	}
}
