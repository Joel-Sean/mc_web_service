package mc.webservice.dao;

import java.util.List;

import mc.webservice.bean.AppEntity;

public interface AppMapper {
	List<AppEntity>  loadApps();
}
