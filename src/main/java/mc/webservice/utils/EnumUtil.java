package mc.webservice.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EnumUtil {

	public static <E extends Enum<E>> E valueOf(Class<E> clazz, Integer ord) {
		if (ord == null)
			return null;
		try {
			return clazz.getEnumConstants()[ord];
		} catch (Exception e) {
			throw new EnumNotFoundException(ord);
		}
	}

	public static <E extends Enum<E>> E valueOf(Class<E> clazz, String value) {
		if (value == null)
			return null;
		try {
			return Enum.valueOf(clazz, value);
		} catch (Exception e) {
			throw new EnumNotFoundException(value);
		}
	}

	public static <K, E> E valueOf(Map<K, E> map, K code) {
		if (code == null)
			return null;
		E result = map.get(code);
		if (result != null) {
			return result;
		}
		throw new EnumNotFoundException(code);
	}

	public static <K extends Object, E extends CodableEnum<K>> Map<K, E> mapByCode(
			E[] values) {
		HashMap<K, E> map = new HashMap<K, E>();
		for (E value : values) {
			map.put(value.getCode(), value);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <K extends Object, E extends CodableEnum<K>> E codeOf(
			Class<E> clazz, K code) {
		try {
			Field field = clazz.getDeclaredField("codeMap");
			HashMap<K, E> map = (HashMap<K, E>) field.get(null);
			return valueOf(map, code);
		} catch (Exception e) {
//			e.printStackTrace();
			throw new EnumNotFoundException(code);
		}
	}

	public static <K extends Object> K getCode(CodableEnum<K> e) {
		if (e == null) {
			return null;
		}
		return e.getCode();
	}

}
