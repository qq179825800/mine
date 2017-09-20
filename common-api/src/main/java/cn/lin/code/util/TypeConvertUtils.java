/**  
 * @Title: TypeConvertUtils.java
 * @Package cn.getgrid.common.api.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author wanghl
 * @date 2016年9月23日 下午3:20:04
 * @version V1.0  
 */
package cn.lin.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Title: TypeConvertUtils.java
 * @Package cn.getgrid.common.api.util
 * @Description: 类型转换工具类
 * @author wanghl
 * @date 2016年9月23日 下午3:20:04
 * @version V1.0
 */
public class TypeConvertUtils {
	final static Logger logger = LoggerFactory.getLogger(TypeConvertUtils.class);

	/**
	* @Title: str2Date
	* @Description: 将字符串转化为date类型
	* @param datestr
	* @return 
	* Date 
	* @author wanghl 
	* @date 2016年9月23日 下午3:52:42 
	* @throws
	 */
	public static Date str2Date(String datestr) {
		if (!StringUtils.isEmpty(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.US);
			return sdf.parse(datestr);
		} catch (Exception e) {
			logger.error("将字符串转化为Date类型失败，失败信息如下：\n" + e.getMessage());
			return null;
		}
	}

	/**
	* @Title: date2Str
	* @Description: 日期转化为String
	* @param date
	* @return 
	* String 
	* @author wanghl 
	* @date 2016年9月23日 下午3:52:32 
	* @throws
	 */
	public static String date2Str(Date date) {
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			return sdf.format(date);
		} catch (Exception e) {
			logger.error("将日期转化为String类型失败，失败信息如下：\n" + e.getMessage());
			return null;
		}
	}

	/**
	* @Title: str2Timestamp
	* @Description: 字符串转化为时间戳类型
	* @param timeStr
	* @return 
	* Timestamp 
	* @author wanghl 
	* @date 2016年9月23日 下午3:51:55 
	* @throws
	 */
	public static Timestamp str2Timestamp(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return null;
		}
		try {
			return Timestamp.valueOf(timeStr);
		} catch (Exception e) {
			logger.error("将字符串转化为时间戳类型失败，失败信息如下：\n" + e.getMessage());
			return null;
		}
	}

	/**
	* @Title: timestamp2Str
	* @Description: 时间戳转化为字符串类型
	* @param timestamp
	* @return 
	* String 
	* @author wanghl 
	* @date 2016年9月23日 下午3:52:03 
	* @throws
	 */
	public static String timestamp2Str(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			tsStr = sdf.format(timestamp);
		} catch (Exception e) {
			logger.error("将时间戳转化为字符串类型失败，失败信息如下：\n" + e.getMessage());
			tsStr = null;
		}
		return tsStr;
	}
	
	/**
	* @Title: timestamp2Date
	* @Description: 时间戳转化为Date类型
	* @param timestamp
	* @return 
	* Date 
	* @author wanghl 
	* @date 2016年9月23日 下午3:52:09 
	* @throws
	 */
	public static Date timestamp2Date(Timestamp timestamp){
		Date date = new Date();
		try {
			date = timestamp;
		} catch (Exception e) {
			logger.error("将时间戳转化为Date类型失败，失败信息如下：\n" + e.getMessage());
			date = null;
		}
		
		return date;
	}
	
	/**
	* @Title: date2Timestamp
	* @Description: Date类型转化为时间戳类型
	* @param date
	* @return 
	* Timestamp 
	* @author wanghl 
	* @date 2016年9月23日 下午3:52:15 
	* @throws
	 */
	public static Timestamp date2Timestamp(Date date){
		if(date == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(sdf.format(date));
		} catch (Exception e) {
			logger.error("将Date类型转化为时间戳类型失败，失败信息如下：\n" + e.getMessage());
			ts = null;
		}
		return ts;
	}
}
