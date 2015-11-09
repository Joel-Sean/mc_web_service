package mc.webservice.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ActionBeanUtil {

	private static Log logger = LogFactory.getLog(ActionBeanUtil.class);
	
	public static KeyValue checkInput(Object entity, String...propertyNames) {
		if (entity == null) {
			return null;
		}
		for (String propertyName : propertyNames) {
			KeyValue error = checkOneInput(entity, propertyName);
			if (error != null) {
				return error;
			}
		}
		return null;
	}
	
	private static KeyValue checkOneInput(Object entity, String propertyName) {
		if (StringUtil.isEmpty(propertyName)) {
			return null;
		}		
		String propertyValue;
		try {
			propertyValue = BeanUtils.getProperty(entity, propertyName);
		} catch (Exception e) {
			logger.error("Cannot read property [" + propertyName + "] from entity [" + entity + "].", e);
			return null;
		}			
		
		if (propertyValue == null || StringUtil.isEmpty(propertyValue.trim())) {
			return BaseAction.FIELD_REQUIRED.withNewValue(
					propertyName + " " + BaseAction.FIELD_REQUIRED.getValue());
		}
		return null;
	}

	public static void enrichSearchInput(Object entity, String...propertyNames) {
		if (entity == null) {
			return;
		}
		for (String propertyName : propertyNames) {
			enrichOneSearchInput(entity, propertyName);
		}
	}
	
	/**
	 * @param entity
	 * @param propertyName
	 * 
	 * This method:
	 * 
	 * 	1) If the property value is null, let it be and return;
	 * 	2) Trim the property value;
	 * 	3) After trim, if the property is empty string (ie. ""), change it to null;
	 * 	4) More to add, if you like. 
	 * 
	 */
	private static void enrichOneSearchInput(Object entity, String propertyName) {
		if (StringUtil.isEmpty(propertyName)) {
			return;
		}
		
		String propertyValue;
		try {
			propertyValue = BeanUtils.getProperty(entity, propertyName);
		} catch (Exception e) {
			logger.error("Cannot read property [" + propertyName + "] from entity [" + entity + "].", e);
			return;
		}
		if (propertyValue == null) {
			// Do nothing and let it remain null.
			return;
		}
		propertyValue = propertyValue.trim();
		if ("".equals(propertyValue)) {
			propertyValue = null;
		}
		try {
			BeanUtils.setProperty(entity, propertyName, propertyValue);
		} catch (Exception e) {
			logger.error("Cannot set enriched property [" + propertyName + "] on entity [" + entity + "].", e);
		}
		
	}	

}
