package mc.webservice.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringInit  implements ServletContextListener {
	
	    
	    public SpringInit() {
	        super();
	    }
	    @Override
	    public void contextInitialized(ServletContextEvent event) {
//	        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	    	
	    	String relativePath = event.getServletContext().getInitParameter("contextConfigLocation");
	    	String realPath = event.getServletContext().getRealPath(relativePath);
	    	SpringBeanFactory.init(realPath);
	    }
	    
	    @Override
	    public void contextDestroyed(ServletContextEvent event) {
	    	SpringBeanFactory.clear();
	    }
}
