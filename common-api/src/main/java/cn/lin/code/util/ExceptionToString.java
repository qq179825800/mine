package cn.lin.code.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
* @Title: ExceptionToString.java
* @Package cn.getgrid.common.api.util
* @Description: 将异常对象转化为字符串
* @author wanghl
* @date 2016年10月11日 上午11:44:37
* @version V1.0
 */
public class ExceptionToString {

	/**
	 * 
	* @Title: getExString
	* @Description: 将异常信息转化为字符串
	* @param e 异常对象
	* @return String 转化后的字符串
	* @author wanghl 
	* @date 2016年10月11日 上午11:44:46 
	* @throws
	 */
	public static String getExString(Exception e) {
		return getExString(e, false);
	}

	/**
	 * 
	* @Title: getExString
	* @Description: 将异常信息转化为字符串
	* @param throwable 异常对象
	* @param isAppendDate true:在字符串的头部添加当前的时间信息,false:不添加时间信息
	* @return 
	* String 
	* @author wanghl 
	* @date 2016年10月11日 上午11:45:11 
	* @throws
	 */
	public static String getExString(Throwable throwable, boolean isAppendDate) {
		if (throwable == null) {
			return "error info is null object";
		}
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw, true);
			throwable.printStackTrace(pw);
			String errorStr = sw.getBuffer().toString();
			if (isAppendDate) {
				errorStr = DateTimeUtils.getCurrentDate("") + ":\r\n" + errorStr + "\r\n";
			}
			return errorStr;
		} catch (Exception e2) {
			return "bad getErrorInfoFromException";
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					return "bad getErrorInfoFromException";
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 
	* @Title: getStackTrace
	* @Description: 将异常堆栈信息转化为字符串
	* @param throwable 异常对象
	* @return 
	* String 转化后的字符串
	* @author wanghl 
	* @date 2016年10月11日 上午11:44:22 
	* @throws
	 */
	public static String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}
}
