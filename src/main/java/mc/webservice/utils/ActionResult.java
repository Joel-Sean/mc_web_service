package mc.webservice.utils;

public class ActionResult<T> {

	private KeyValue result;
	private T data;

	public ActionResult(KeyValue result , T data) {
		super();
		this.result = result;
		this.data = data;
	}

	public int getCode() {
		return result.key;
	}

	public String getMessage() {
		return result.value;
	}

	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
}
