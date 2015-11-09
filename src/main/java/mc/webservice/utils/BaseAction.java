package mc.webservice.utils;

import com.opensymphony.xwork2.Action;

public class BaseAction {

	protected ActionResult<Object> result;
	protected final static KeyValue FORMAT_ERROR = new KeyValue(-110109, "format error.");
	protected final static KeyValue FIELD_REQUIRED = new KeyValue(-110110, "field input is required.");
	private static String LIST_ACTION = "list";
	private static KeyValue dummyResult = new KeyValue(0, "");
	
	protected String error(int errorCode) {
		return result(new KeyValue(errorCode, ""), null, Action.ERROR);
	}
	
	protected String error(KeyValue result) {
		return result(result, null, Action.ERROR);
	}
	
	protected String error(KeyValue result, Object model) {
		return result(result, model, Action.ERROR);
	}
	
	protected String success(int code) {
		return success(code, null);
	}
	
	protected String success(int code, Object model) {
		return result(new KeyValue(code, ""), model, Action.SUCCESS);
	}
	
	protected String success(KeyValue result) {
		return result(result, null, Action.SUCCESS);
	}
	
	protected String success(KeyValue result, Object model) {
		return result(result, model, Action.SUCCESS);
	}
	
	protected String result(KeyValue result, String returnValue) {
		return result(result, null, returnValue);
	}
	
	protected String result(KeyValue result, Object model, String returnValue) {
		this.result = new ActionResult<Object>(result, model);
		return returnValue;
	}
	
	protected String success(Object model) {
		return result(dummyResult, model, LIST_ACTION);
	}
	
	public ActionResult<Object> getResult() {
		return result;
	}

}
