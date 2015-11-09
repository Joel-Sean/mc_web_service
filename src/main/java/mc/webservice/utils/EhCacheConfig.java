package mc.webservice.utils;

public class EhCacheConfig {
	
	private String cacheNameLoginCache;
	private String cacheNameLogin;
	private String cacheNameLogin2;
	private String cacheNameLogin3;
	
	
	public String getCacheNameLogin3() {
		return cacheNameLogin3;
	}
	public void setCacheNameLogin3(String cacheNameLogin3) {
		this.cacheNameLogin3 = cacheNameLogin3;
	}
	public String getCacheNameLoginCache() {
		return cacheNameLoginCache;
	}
	public void setCacheNameLoginCache(String cacheNameLoginCache) {
		this.cacheNameLoginCache = cacheNameLoginCache;
	}
	public String getCacheNameLogin() {
		return cacheNameLogin;
	}
	public void setCacheNameLogin(String cacheNameLogin) {
		this.cacheNameLogin = cacheNameLogin;
	}
	public String getCacheNameLogin2() {
		return cacheNameLogin2;
	}
	public void setCacheNameLogin2(String cacheNameLogin2) {
		this.cacheNameLogin2 = cacheNameLogin2;
	}
	@Override
	public String toString() {
		return "EhCacheConfig [cacheNameLoginCache=" + cacheNameLoginCache
				+ ", cacheNameLogin=" + cacheNameLogin + ", cacheNameLogin2="
				+ cacheNameLogin2 + ", cacheNameLogin3=" + cacheNameLogin3 + "]";
	}

}
