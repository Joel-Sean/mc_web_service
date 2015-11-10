package mc.webservice.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SpringInit  implements ServletContextListener {
	
	    
	    public SpringInit() {
	        super();
	    }
	    @Override
	    public void contextInitialized(ServletContextEvent event) {
	     	
	    	ServletContext context = event.getServletContext();
	    	SpringBeanFactory.init(context);
	    	
	    }
	    
	    @Override
	    public void contextDestroyed(ServletContextEvent event) {
	    	SpringBeanFactory.clear();
	    }
}
