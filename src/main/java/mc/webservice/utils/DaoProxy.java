package mc.webservice.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class DaoProxy {

	public static final String LOAD_PREFIX = "load";
	public static final String SAVE_PREFIX = "save";
	private static final Logger log = Logger.getLogger(DaoProxy.class);

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<T> type, T... objects) {
		if (log.isDebugEnabled()) {
			for (T object : objects) {
				log.debug("Create Underline Object: " + object);
			}
		}
		T newProxyInstance = (T) Proxy.newProxyInstance(type.getClassLoader(),
				new Class[] { type }, new DaoInvocationHandler<T>(type, objects));
		return newProxyInstance;
	}

	private static class DaoInvocationHandler<T> implements InvocationHandler {
		private final T[] workers;
		private final HashMap<Method, Method> loadSaveMap = new HashMap<Method, Method>();
		
		private DaoInvocationHandler(Class<T> type, T... workers) {
			Method[] methods = type.getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if (!methodName.startsWith(LOAD_PREFIX)) {
					continue;
				}
				Class<?> returnType = method.getReturnType();
				String saveMethodName = methodName.replaceAll(LOAD_PREFIX, SAVE_PREFIX);
				for (Method saveMethod : methods) {
					if (!saveMethod.getName().equals(saveMethodName)) {
						continue;
					}
					Class<?>[] params = saveMethod.getParameterTypes();
					if (params != null && params.length == 1 && params[0].equals(returnType)) {
						loadSaveMap.put(method, saveMethod);
						break;
					}
				}
			}
			this.workers = workers;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			Object result = null;
			if (method.getName().startsWith(LOAD_PREFIX)) {
				int i = 0;
				for (; i<workers.length; i++) {
					T worker = workers[i];
					if (log.isDebugEnabled())
						log.debug("Invoking Method: " + method.getName() + " on " + workers);
					result = method.invoke(worker, args);
					if (result != null) {
						break;
					}
				}
				
				if (result != null) {
					Method saveMethod = loadSaveMap.get(method);
					if (saveMethod != null) {
						for (int j=0; j<i; j++) {
							saveMethod.invoke(workers[j], new Object[]{result});
						}
					}
				}
				
				return result;
			} else if (method.getName().startsWith(SAVE_PREFIX)) {
				for (T worker : workers) {
					if (log.isDebugEnabled())
						log.debug("Invoking Method: " + method.getName()
								+ " on " + workers);
					result = method.invoke(worker, args);
					if (result != null) {
						return result;
					}
				}
				return result;
			} else {
				for (T worker : workers) {
					if (log.isDebugEnabled())
						log.debug("Invoking Method: " + method.getName()
								+ " on " + workers);
					result = method.invoke(worker, args);
					if (result != null) {
						return result;
					}
				}
				return result;
			}
		}
	}

}
