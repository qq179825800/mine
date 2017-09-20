/**  
 * @Title: DateTimeUtils.java
 * @Package cn.getgrid.common.api.util
 * @Description: TODO(日期时间工具类)
 * @author wanghl
 * @date 2016年10月11日 上午11:37:59
 * @version V1.0  
 */
package cn.lin.code.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title: DateTimeUtils.java
 * @Package cn.getgrid.common.api.util
 * @Description: (日期时间工具类)
 * @author wanghl
 * @date 2016年10月11日 上午11:37:59
 * @version V1.0
 */
public class DateTimeUtils {

	/**
	 * 
	* @Title: getCurrentDate
	* @Description: 获取当前日期,如果dateFormat为空,则默认返回yyyy-MM-dd HH:mm:ss格式的日期
	* @param dateFormat 日期格式 比如yyyy-MM-dd HH:mm:ss
	* @return 
	* String 
	* @author wanghl 
	* @date 2016年10月11日 上午11:39:48 
	* @throws
	 */
	public static String getCurrentDate(String dateFormat) {
		if (dateFormat == null || dateFormat.trim().equals("")) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

	/**
	 * 
	* @Title: formatDate
	* @Description: 格式化日期
	* @param date 日期
	* @param dateFormat 日期格式 比如yyyy-MM-dd HH:mm:ss
	* @return 
	* String 
	* @author wanghl 
	* @date 2016年10月11日 上午11:40:07 
	* @throws
	 */
	public static String formatDate(Date date, String dateFormat) {
		if (dateFormat == null || dateFormat.trim().equals("")) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(dateFormat).format(date);
	}

	/**
	 * 
	* @Title: strToDate
	* @Description: 将字符串转成日期,如果dateFormat为空,则默认返回yyyy-MM-dd HH:mm:ss格式的日期
	* @param str
	* @param dateFormat
	* @return
	* @throws ParseException 
	* Date 
	* @author wanghl 
	* @date 2016年10月11日 上午11:40:36 
	* @throws
	 */
	public static Date strToDate(String str, String dateFormat) throws ParseException {
		if (dateFormat == null || dateFormat.trim().equals("")) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(dateFormat).parse(str);
	}

	/**
	 * 
	* @Title: getDifferDay
	* @Description: 获取一个日期前days天或后day天的日期
	* @param date
	* @param days 天数，前为负数，后为正数
	* @return 
	* Date 
	* @author wanghl 
	* @date 2016年10月11日 上午11:40:53 
	* @throws
	 */
	public static Date getDifferDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 
	* @Title: getDifferHour
	* @Description: 获取一个日期前hours小时或后hours小时的日期
	* @param date
	* @param hours 小时数，前为负数，后为正数
	* @return 
	* Date 
	* @author wanghl 
	* @date 2016年10月11日 上午11:42:05 
	* @throws
	 */
	public static Date getDifferHour(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 
	* @Title: getDifferMinute
	* @Description: 获取一个日期前minute分钟或后minute分钟的日期
	* @param date
	* @param minute 分钟数，前为负数，后为正数
	* @return 
	* Date 
	* @author wanghl 
	* @date 2016年10月11日 上午11:42:26 
	* @throws
	 */
	public static Date getDifferMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * @说明：获取一个日期前minute秒或后minute秒的日期
	 * @author 张春雷
	 * @param date
	 * @param minute 秒数，前为负数，后为正数
	 * @return
	 * @return Date
	 * @2016年12月5日 下午5:33:38
	 */
	public static Date getDifferSecond(Date date, Integer minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, minute);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 
	* @Title: getTwoDateDifferDays
	* @Description: 获取两个日期相差天数
	* @param date1
	* @param date2
	* @return 
	* int 
	* @author wanghl 
	* @date 2016年10月11日 上午11:42:54 
	* @throws
	 */
	public static int getTwoDateDifferDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return getDaysBetween(cal1, cal2);
	}

	/**
	 * 
	* @Title: getDaysBetween
	* @Description: 获取两个日期相差天数
	* @param d1
	* @param d2
	* @return 
	* int 
	* @author wanghl 
	* @date 2016年10月11日 上午11:43:24 
	* @throws
	 */
	private static int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}
	
	public static String formatDate(Timestamp ts, String dateFormat) {
		if (dateFormat == null || dateFormat.trim().equals("")) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(dateFormat).format(ts);
	}
	
}
