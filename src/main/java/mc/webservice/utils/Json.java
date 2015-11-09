package mc.webservice.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	}
	private Json() {}	
	
	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}		
		try {
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new SxException(-110016, "Json serialization error: " + e.getMessage());
		}
	}

	public static <T> T fromJson(String json, Class<T> clazz) {		
		if (StringUtil.isEmpty(json)) {
			return null;
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonMappingException e) {
			throw new SxException(-110006,"Json deserialization error: " + e.getMessage());
		} catch (IOException e) {		
			throw new SxException(-110007,"Json io error: " + e.getMessage());
		}
	}

	public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {		
		if (StringUtil.isEmpty(json)) {
			return null;
		}
		try {
			return mapper.readValue(json, mapper.getTypeFactory()
					.constructCollectionType(List.class, clazz));
		} catch (JsonMappingException e) {
			throw new SxException(-110006,"Json deserialization error: " + e.getMessage());
		} catch (IOException e) {
			throw new SxException(-110007,"Json io error: " + e.getMessage());
		}
	}	
	
}
