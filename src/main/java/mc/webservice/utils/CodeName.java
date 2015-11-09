package mc.webservice.utils;

public class CodeName {
	
	private int code;
	private String name;
	
	public CodeName() {
		super();
	}
	
	public CodeName(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
