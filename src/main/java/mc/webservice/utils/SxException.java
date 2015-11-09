package mc.webservice.utils;

public class SxException extends RuntimeException {

	private static final long serialVersionUID = 9208578517065648720L;
	private int errorCode = -1;
	private String errorText = null;

	public SxException(int errorCode) {
		this.errorCode = errorCode;
	}

	public SxException(int errorCode, String errorText) {
		super(errorText);
		this.errorCode = errorCode;
		this.errorText = errorText;
	}
	
	public SxException(String msg) {
		super(msg);
	}
	
	public SxException(int errCode, Throwable t) {
		super(t);
		this.errorCode = errCode;
	}

	public SxException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

}
