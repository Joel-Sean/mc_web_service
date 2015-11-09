package mc.webservice.utils;

public class EnumNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1056538647066221114L;
	public final Object code;

	public EnumNotFoundException (Object code) {
		super("Code is not found: " + code);
		this.code = code;
	}
			
}
