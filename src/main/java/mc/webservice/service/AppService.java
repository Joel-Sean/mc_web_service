package mc.webservice.service;

import java.util.List;

import mc.webservice.bean.AppEntity;

public interface AppService {

	public List<AppEntity> loadApps();
}
