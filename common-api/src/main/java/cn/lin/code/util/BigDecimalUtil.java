package cn.lin.code.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println((3 * 0.04 + 5 * 0.06) * 0.07);
		System.out.println(multiply(add(multiply(3, 0.04), multiply(5, 0.06)), 0.07));

	}

	public static double add(double a, double b) {

		BigDecimal bd1 = new BigDecimal(Double.toString(a));
		BigDecimal bd2 = new BigDecimal(Double.toString(b));

		return bd1.add(bd2).doubleValue();
	}

	public static double subtract(double a, double b) {

		BigDecimal bd1 = new BigDecimal(Double.toString(a));
		BigDecimal bd2 = new BigDecimal(Double.toString(b));

		return bd1.subtract(bd2).doubleValue();
	}

	public static double multiply(double a, double b) {

		BigDecimal bd1 = new BigDecimal(Double.toString(a));
		BigDecimal bd2 = new BigDecimal(Double.toString(b));

		return bd1.multiply(bd2).doubleValue();
	}

	public static double divide(double a, double b, int scale) {

		BigDecimal bd1 = new BigDecimal(Double.toString(a));
		BigDecimal bd2 = new BigDecimal(Double.toString(b));

		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double scale(double a, int scale ) {

		BigDecimal bd1 = new BigDecimal(Double.toString(a));

		return bd1.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
	
	public static String doubleAsString(double a) {
		BigDecimal bd = new BigDecimal(Double.toString(a));
		
		return bd.toString();
	}
	
}
