package cn.lin.code.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormartValidationUtil {

	/**
	 * @说明：手机号验证
	 * @author 张春雷
	 * @param cellphone
	 * @return
	 * @return boolean
	 * @2017年6月21日 下午5:36:03
	 */
	public static boolean cellphoneValidation(String cellphone){
		String pattern = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(cellphone);
		return m.matches();
	}
}
