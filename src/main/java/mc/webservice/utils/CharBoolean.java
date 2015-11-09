package mc.webservice.utils;

public enum CharBoolean {
	Y, N;
	
	public boolean toBoolean() {
		return this == Y;
	}
	
}
