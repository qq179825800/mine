package cn.lin.code.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @version 1.0
 * @author lxf
 * 
 * 2016年9月20日  上午11:59:24
 */
public class MapUtils {

	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<String, Object>(
				new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
}


class MapKeyComparator implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {
		
		return str1.compareTo(str2);
	}
}
