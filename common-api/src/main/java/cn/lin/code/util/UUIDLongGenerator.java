package cn.lin.code.util;

/**
 * @author xr
 */
public class UUIDLongGenerator {

	private static int count = 10000;
	private static int step = 0;

	/**
	 * 生成18位长整形数字
	 * 
	 * @return
	 */
	public static synchronized long generateId() {

		if (count > 99999) {
			step++;
			count = 10000;
		}
		return Long.parseLong("" + (System.currentTimeMillis() + step) + (count++));
	}

}
