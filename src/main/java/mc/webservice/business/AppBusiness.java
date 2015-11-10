package mc.webservice.business;

import mc.webservice.bean.AppEntity;
import mc.webservice.bean.PublishList;
import mc.webservice.service.AppService;
import mc.webservice.utils.SpringBeanFactory;

import org.springframework.context.ApplicationContext;

public class AppBusiness {
	
	
ApplicationContext ctx = SpringBeanFactory.getContext();
	
	private AppService appService = (AppService) ctx.getBean("appService");
	//private AppEntity ae = (AppEntity) ctx.getBean("AppEntity");
	
	
	
	public PublishList getPublishInfo(PublishList pl){
		
		AppEntity ae  = appService.loadApps().get(0);		
		pl.setPl(ae.getAppName());
		return pl;
		
		
	}
}
