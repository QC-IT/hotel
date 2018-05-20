package com.hotel.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	 private static double EARTH_RADIUS = 6378.137; 
	@org.junit.Test
	public void test() {
		double longitude=123.7;
		double latitude=45.4;
		Shop[] s = new Shop[1000000];
	for(int i=0;i<s.length;i++){
		s[i]=new Shop();
		s[i].setName(String.valueOf(i));
		s[i].setLongitude(120.0+0.0001*i);
		s[i].setLatitude(40.0+0.00001*i);
	}

		List<Shop> list = Arrays.asList(s);
		long begin=System.currentTimeMillis();
	list.forEach(ss->{ss.setDistance(getDistance(String.valueOf(ss.getLatitude()), String.valueOf(ss.getLongitude()), String.valueOf(latitude), String.valueOf(longitude)));});
List<Shop> shops=list.parallelStream().sorted((s1,s2)->{if( s1.getDistance()>s2.getDistance())return 1; if( s1.getDistance()<s2.getDistance())return -1;else return 0;}).collect(Collectors.toList());
long end=System.currentTimeMillis();
//shops.forEach(System.out::println);
System.out.println(end-begin+"ms");
	}
	 /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1 用户经度
     * @param lng1 用户纬度
     * @param lat2 商家经度
     * @param lng2 商家纬度
     * @return 两地的距离（单位为KM）
     */
    public static long getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);
          
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr.
            substring(0, distanceStr.indexOf("."));
          
        return Long.parseLong(distanceStr);
    }
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
}

class Shop {
	private String name;
	private double longitude;
	private double latitude;
	private long distance;

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public long getDistance() {
		return this.distance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return latitude;
	}
	public String toString() {
		return "[ name:" + this.name + ",longitude:" + this.longitude + ",latitude:" + this.latitude + ",distance:"
				+ this.distance + " ]";
	}
}