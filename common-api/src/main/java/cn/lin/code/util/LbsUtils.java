package cn.lin.code.util;

/**
 * 类说明
 *
 * @author ssz
 *
 * 创建时间：2016年11月21日 下午6:47:38
 */
public class LbsUtils {
	
	public static double EARTH_RADIUS = 6378.137;

	/**
	 * 将坐标转换为弧度
	 * 
	 * @param d
	 * @return
	 */
	private static double rad(double d){
		return d * Math.PI / 180.0;
	}
	
	/**
	 * 
	 * 计算两个经纬度之间的距离
	 * 
	 * @param x
	 * @param y
	 */
//	public static double getDistance(double x, double y) {
//
//		double y1 = 43.858857+0.0009;
//		double x1 = 125.331928+0.0009788;
//		double y2 = 43.859363;
//		double x2 = 125.332793;
//		double jieguo = getDistance(y1, x1, y2, x2);
//		double jieguo2 = jieguo * 1000;
//		double jieguo3 = Math.round(jieguo2);
//		DecimalFormat df = new DecimalFormat("#####0.0");
//		DecimalFormat dfa = new DecimalFormat("#####0");
//		if (jieguo3 >= 1000) {
//			System.out.println(df.format(jieguo3 / 1000) + "公里");
//		} else {
//			System.out.println(dfa.format(jieguo3) + "米");
//		}
//		return jieguo3;
//	}
	/**
	 * 
	 * 计算两个经纬度之间的距离
	 * 
	 * @param x
	 * @param y
	 */
	public static double getDistance(double y1, double x1, double y2, double x2)

	{
		double radLat1 = rad(y1);
		double radLat2 = rad(y2);
		double a = radLat1 - radLat2;
		double b = rad(x1) - rad(x2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
		Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;
	}
	
	public static void main(String[] args) {
//		System.err.println(getDistance(1, 2));
	}

}
