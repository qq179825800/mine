package cn.lin.code.util;

import java.security.MessageDigest;

public class MD5Util {

	public static void main(String[] args) {

		// System.out.println(MD5Util.getMD5("123456"));
		System.out.println(MD5Util.getMD5("123456"+"147090900046810031"));
		// System.out.println(MD5Util.getMD5(null));
	}

	public final static String getMD5(String str) {

		if (str != null) {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
			try {
				byte[] strBytes = str.getBytes();
				// 获得MD5摘要算法的 MessageDigest 对象
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				// 使用指定的字节更新摘要
				messageDigest.update(strBytes);
				// 获得密文
				byte[] md = messageDigest.digest();
				// 把密文转换成十六进制的字符串形式
				int j = md.length;
				char arr[] = new char[j * 2];
				int k = 0;
				for (int i = 0; i < j; i++) {
					byte byte0 = md[i];
					arr[k++] = hexDigits[byte0 >>> 4 & 0xf];
					arr[k++] = hexDigits[byte0 & 0xf];
				}
				return new String(arr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
