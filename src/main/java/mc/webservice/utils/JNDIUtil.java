package mc.webservice.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIUtil {

	public static Object load(String name) {
		return load("java:comp/env", name);
	}
	
	public static Object load(String context, String name) {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup(context);
			return envCtx.lookup(name);
		} catch (NamingException e) {
//			if (e.getCause() != null) {
//				e.getCause().printStackTrace();
//			}
			throw new RuntimeException(e);
		}
	}

}
