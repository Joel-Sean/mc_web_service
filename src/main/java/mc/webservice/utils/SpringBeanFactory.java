package mc.webservice.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBeanFactory {
	
	private static ApplicationContext context;
	
	/**
	    * 在应用程序启动时配置spring框架
	    *
	    * @param filePath
	    */
	   public static void init(ServletContext eventContext) {

	    if (context == null) {
	       context = WebApplicationContextUtils.getWebApplicationContext(eventContext);
	    }
	  }
	   
	   
	   public static ApplicationContext getContext(){
		    return context;
		   }
	   /**
	    * 方法用于获取业务对象。
	    *
	    * @param beanName
	    * @return
	    */
	   public static Object getBusinessOjbect(String beanName) {
	    return context.getBean(beanName);
	   }
	   /**
	    * 在应用程序关闭时，清空spring框架配置信息。
	    */
	   public static void clear() {
	    if (context != null) {
	     context = null;
	    }
	   }
}
