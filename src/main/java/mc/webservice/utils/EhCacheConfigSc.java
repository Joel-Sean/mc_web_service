package mc.webservice.utils;

public class EhCacheConfigSc {
	
	private String cacheNameLoginScCache;
	private String cacheNameScLogin;
	
	public String getCacheNameLoginCache() {
		return cacheNameLoginScCache;
	}
	public void setCacheNameLoginCache(String cacheNameLoginCache) {
		this.cacheNameLoginScCache = cacheNameLoginCache;
	}
	public String getCacheNameLogin() {
		return cacheNameScLogin;
	}
	public void setCacheNameLogin(String cacheNameLogin) {
		this.cacheNameScLogin = cacheNameLogin;
	}

	@Override
	public String toString() {
		return "EhCacheConfig [cacheNameLoginCache=" + cacheNameLoginScCache
				+ ", cacheNameLogin=" + cacheNameScLogin + "]";
	}

}
