package mc.webservice.utils;

public interface StringCodeEnum {
	
	String getCode();
	
	public static class CodeNotFoundException extends RuntimeException {
		
		private static final long serialVersionUID = 1056538647066221114L;
		public final String code;

		public CodeNotFoundException (String code) {
			super("Code is not found: " + code);
			this.code = code;
		}
				
	}

}
