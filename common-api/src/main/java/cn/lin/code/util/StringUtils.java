package cn.lin.code.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @version 1.0
 * @author lxf
 * 
 * 2016年7月5日  上午11:16:33
 */
public class StringUtils {
	
	/**
	 * 判断字符串是否是空串
	 * 
	 * @param str
	 * 		待判断的字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim())) {
			return true;
		}

		Pattern pattern = Pattern.compile("^[\\s|　]\t\r\n*$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 清除字符串中的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String removeStrSpace(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		Matcher matcher = Pattern.compile("\\s|　|\t|\r|\n").matcher(str);
		return matcher.replaceAll("");
	}
	
	
	/**
	 * 字符串截取
	 * @param str
	 * @param reg
	 * @return
	 */
	public static List<String> split(String str, String regex){
		
		List<String> strList = null;
		if(str==null || str.equals("")){
			return strList;
		}
		
		strList = Arrays.asList(str.split(regex));
		
		return strList;
	}
	
	public static List<Long> splitToLong(String str, String regex){
		
		List<Long> strList = null;
		if(str==null || str.equals("")){
			return strList;
		}
		
		strList = new ArrayList<Long>();
		String[] split = str.split(regex);
		for(String s : split){
			if(!StringUtils.isEmpty(s)){
				strList.add(Long.parseLong(s));
			}
		}
		
		return strList;
	}
	/**
	 * @param list
	 * @return
	 */
	public static String listToString(List<?> list){
		StringBuilder sb = new StringBuilder();   
		for (int i = 0; i < list.size(); i++) {  
		    sb.append(list.get(i)+",");  
		}
		if(sb.toString().endsWith(","))
			return sb.substring(0, sb.length()-1);
		return sb.toString();  
	}
	
	/**
	 * @说明: 把 未知个 对象 组装成一个字符串
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年9月19日 下午1:27:33
	 */
	public static String joinString(Object... objs) {
		StringBuffer sb = new StringBuffer();
		if (objs == null || objs.length <= 0)
			return null;

		for (int i = 0; i < objs.length; i++) {
			sb.append(objs[i]);
		}
		return sb.toString();
	}
	
	/**
	 * @说明：获取随机字符串
	 * @author 张春雷
	 * @param length 字符串长度
	 * @return
	 * @return String
	 * @2016年11月18日 下午2:32:43
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
	}
	
	
}
