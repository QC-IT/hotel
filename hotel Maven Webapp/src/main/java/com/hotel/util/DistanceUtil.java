package com.hotel.util;

/**
 * 距离相关工具类
 * 
 * @author yuanhaohe
 *
 */
public final class DistanceUtil	 {

	private final static double EARTH_RADIUS = 6378.137;

	/**
	 * 根据两个位置的经纬度，来计算两地的距离（单位为KM） 参数为String类型
	 * 
	 * @param lat1
	 *            用户经度
	 * @param lng1
	 *            用户纬度
	 * @param lat2
	 *            商家经度
	 * @param lng2
	 *            商家纬度
	 * @return 两地的距离（单位为KM）
	 */
	public static double getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
		double lat1 = Double.parseDouble(lat1Str);
		double lng1 = Double.parseDouble(lng1Str);
		double lat2 = Double.parseDouble(lat2Str);
		double lng2 = Double.parseDouble(lng2Str);
		return getDistance(lat1, lng1, lat2, lng2);
	}

	/**
	 * 根据两个位置的经纬度，来计算两地的距离（单位为KM） 参数为double类型
	 * 
	 * @param lat1
	 *            用户经度
	 * @param lng1
	 *            用户纬度
	 * @param lat2
	 *            商家经度
	 * @param lng2
	 *            商家纬度
	 * @return 两地的距离（单位为KM）
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double difference = radLat1 - radLat2;
		double mdifference = rad(lng1) - rad(lng2);
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
		distance = distance * EARTH_RADIUS;
		distance = Math.round(distance * 10) / 10.0;
		String distanceStr = String.valueOf(distance);
		return Double.parseDouble(distanceStr);
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
}
