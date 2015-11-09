package mc.webservice.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNotNull {
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	}
	private JsonNotNull() {}	
	
	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}		
		try {
			mapper.setSerializationInclusion(Include.NON_NULL);
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new SxException(-110016, "Json serialization error: " + e.getMessage());
		}
	}
	
	
	
}
