package cn.lin.code.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明
 *
 * @author ssz
 *
 * 创建时间：2016年9月30日 下午1:29:12
 */
public class RegexUtils {
	
	public static boolean isNumber(String str)
    {
        Pattern pattern = Pattern.compile("\\d{1,8}([\\.]\\d{2})?");
        Matcher match = pattern.matcher(str);
        if(match.matches()) {
             return true;
        }
        else {
             return false;
        }
    }
	public static void main(String[] args) {
		Double b = 91999999.57;
		System.err.println(isNumber(String.valueOf(b.longValue())));
		System.err.println(String.valueOf(b.longValue()));
	}
}
