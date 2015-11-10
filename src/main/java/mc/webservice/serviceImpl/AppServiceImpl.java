package mc.webservice.serviceImpl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.webservice.bean.AppEntity;
import mc.webservice.dao.AppMapper;
import mc.webservice.service.AppService;


@Service("appService")
public class AppServiceImpl implements AppService {

	
	@Autowired
	private AppMapper appDao;
	
	
	@Override
	public List<AppEntity> loadApps(){
		 
		return appDao.loadApps();
		
	}
	
	
}
