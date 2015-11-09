package mc.webservice.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author david
 *
 * Using this Util class for any collection related operations.
 * 
 * e.g.: Use  
 * 
 * Map<AccountTypeEntity.Code, ProductionIntegration> integrations 
 * 		= CollectionUtil.createGenericMap(); 
 * 
 * instead of 
 * 
 * Map<AccountTypeEntity.Code, ProductionIntegration> integrations 
 * 		= new HashMap<AccountTypeEntity.Code, ProductionIntegration>();
 * 
 */
public class CollectionUtil {
	public static <V> boolean isEmpty(Collection<V> c) {
		return c == null || c.isEmpty();
	}
	public static <V> List<V> createGenericList() {
		return new ArrayList<V>();
	}	
	public static <V> List<V> createGenericList(Collection<V> c) {
		return new ArrayList<V>(c);
	}	
	public static <V> List<V> createGenericList(V[] a) {
		return Arrays.asList(a);
	}		
	public static <V> List<V> createGenericList(V v) {
		List<V> l = new ArrayList<V>();
		l.add(v);
		return l;
	}		
	public static <K, V> Map<K, V> createGenericMap() {
		return new HashMap<K, V>();
	}
	public static <V> Set<V> createGenericSet() {
		return new HashSet<V>();
	}		
	
	public static <V, T> List<V> convertList(List<T> list, ListConvertor<V, T> convertor) {
		List<V> result = createGenericList();
		for (T t : list) {
			result.add(convertor.convert(t));
		}
		return result;
	}
	
	public interface ListConvertor<V, T> {
		V convert(T t);
	}
}
