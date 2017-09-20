package cn.lin.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ClassRefUtil.java
 * @Package cn.getgrid.common.api.util
 * @Description: 通过Java反射机制，操作对象属性
 * @author wanghl
 * @date 2016年9月23日 下午2:47:50
 * @version V1.0
 */
public class ClassRefUtil {
	final static Logger logger = LoggerFactory.getLogger(ClassRefUtil.class);

	/**
	* @Title: checkFieldIsNotNull
	* @Description: 判断对象中的属性值是否为空
	* @param bean
	* @param fields
	* @return 
	* boolean 
	* @author wanghl 
	* @date 2016年9月23日 下午4:07:15 
	* @throws
	 */
	public static boolean checkFieldIsNotNull(Object bean, String... fields) {
		boolean flag = true;
		if (bean != null) {
			// 获取对象中所有的属性及其值
			Map<String, String> fieldValueMap = getFieldValueMap(bean);
			if(fields != null && fields.length > 0){
				if (fieldValueMap != null && !fieldValueMap.isEmpty()) {
					for (String field : fields) {
						if (!StringUtils.isEmpty(field)) {
							String value = fieldValueMap.get(field);
							if (StringUtils.isEmpty(value)) {
								flag = false;
								break;
							}
						}
					}
				}
			}else{
				// 如果没有传递属性，则判断所有属性
				for (String value : fieldValueMap.values()) {  
					if (StringUtils.isEmpty(value)) {
						flag = false;
						break;
					}
				}  
			}
		}
		return flag;
	}

	/**
	 * @Title: getFieldValueMap
	 * @Description: 取对象的属性和值对应关系的MAP
	 * @param bean
	 * @return Map<String,String>
	 * @author wanghl
	 * @date 2016年9月23日 下午2:54:15
	 * @throws
	 */
	public static Map<String, String> getFieldValueMap(Object bean) {
		Class<?> cls = bean.getClass();
		Map<String, String> valueMap = new HashMap<String, String>();
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldType = field.getType().getSimpleName();
				String fieldGetName = parGetName(field.getName());
				if (!checkGetMet(methods, fieldGetName)) {
					continue;
				}
				Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
				Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
				String result = null;
				if ("Date".equals(fieldType)) {
					result = TypeConvertUtils.date2Str((Date) fieldVal);
				} else if ("Timestamp".equals(fieldType)) {
					TypeConvertUtils.timestamp2Str((Timestamp) fieldVal);
				} else {
					if (null != fieldVal) {
						result = String.valueOf(fieldVal);
					}
				}
				valueMap.put(field.getName(), result);
			} catch (Exception e) {
				logger.error("获取属性值失败，失败信息如下：\n" + e.getMessage());
				continue;
			}
		}
		return valueMap;
	}

	/**
	 * @Title: setFieldValue
	 * @Description: 为对象设置属性值
	 * @param bean
	 * @param valMap
	 *            void
	 * @author wanghl
	 * @date 2016年9月23日 下午2:59:34
	 * @throws
	 */
	public static void setFieldValue(Object bean, Map<String, String> valMap) {
		Class<?> cls = bean.getClass();
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldSetName = parSetName(field.getName());
				if (!checkSetMet(methods, fieldSetName)) {
					continue;
				}
				Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
				String value = valMap.get(field.getName());
				if (null != value && !"".equals(value)) {
					String fieldType = field.getType().getSimpleName();
					if ("String".equals(fieldType)) {
						fieldSetMet.invoke(bean, value);
					} else if ("Timestamp".equals(fieldType)) {
						Timestamp temp = TypeConvertUtils.str2Timestamp(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Date".equals(fieldType)) {
						Date temp = TypeConvertUtils.str2Date(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
						Integer intval = Integer.parseInt(value);
						fieldSetMet.invoke(bean, intval);
					} else if ("Long".equalsIgnoreCase(fieldType)) {
						Long temp = Long.parseLong(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Double".equalsIgnoreCase(fieldType)) {
						Double temp = Double.parseDouble(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Boolean".equalsIgnoreCase(fieldType)) {
						Boolean temp = Boolean.parseBoolean(value);
						fieldSetMet.invoke(bean, temp);
					} else {
						logger.info("转换" + fieldType + "类型失败！");
					}
				}
			} catch (Exception e) {
				logger.error("为对象设置属性值失败，失败原因如下：\n" + e.getMessage());
				continue;
			}
		}
	}

	/**
	 * @Title: checkSetMet
	 * @Description: 判断是否存在某属性的 set方法
	 * @param methods
	 * @param fieldSetMet
	 * @return boolean
	 * @author wanghl
	 * @date 2016年9月23日 下午3:06:25
	 * @throws
	 */
	private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
		for (Method met : methods) {
			if (fieldSetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Title: checkGetMet
	 * @Description: 判断是否存在某属性的 get方法
	 * @param methods
	 * @param fieldGetMet
	 * @return boolean
	 * @author wanghl
	 * @date 2016年9月23日 下午3:06:39
	 * @throws
	 */
	private static boolean checkGetMet(Method[] methods, String fieldGetMet) {
		for (Method met : methods) {
			if (fieldGetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Title: parGetName
	 * @Description: 拼接某属性的 get方法
	 * @param fieldName
	 * @return String
	 * @author wanghl
	 * @date 2016年9月23日 下午3:07:18
	 * @throws
	 */
	private static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * @Title: parSetName
	 * @Description: 拼接在某属性的 set方法
	 * @param fieldName
	 * @return String
	 * @author wanghl
	 * @date 2016年9月23日 下午3:07:32
	 * @throws
	 */
	private static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}
