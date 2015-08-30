/**
 * 
 */
package com.revencoft.basic_access.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author mengqingyan
 * @version 
 */
public abstract class CollectionUtil {

	/**
	 * 将逗号分隔的字符串转换成set
	 * @param s
	 * @return
	 */
	 public static Set<String> commaDelimitedStringToSet(String s) {
	        Set<String> set = new HashSet<String>();
	        String[] split = s.split(",");
	        for (String aSplit : split) {
	            String trimmed = aSplit.trim();
	            if (trimmed.length() > 0)
	                set.add(trimmed);
	        }
	        return set;
	    }
	 
	 /**
	  * 校验map不为null且不为空
	  * @param map
	  * @param message
	  * @param values
	  */
	 public static <K, V> void checkNotEmptyMap(final Map<K, V> map, final String message, final Object... values) {
			if (map == null) {
	            throw new NullPointerException(String.format(message, values));
	        }
	        if (map.size() == 0) {
	            throw new IllegalArgumentException(String.format(message, values));
	        }
		}
		
}
