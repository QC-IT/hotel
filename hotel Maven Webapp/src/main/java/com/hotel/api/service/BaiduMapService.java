package com.hotel.api.service;

import java.util.Map;

import com.hotel.exceptions.BaiduMapLocationFormatException;
/**
 * 
 * @author yuanhaohe
 *百度地图服务接口
 */
public interface BaiduMapService {

	/**
	 * @author yuanhaohe
	 * @param location 经纬度（如35.658651,139.745415）纬度在前经度在后，中间以","隔开 。
	 * @return 百度地图得到的对应城市名称
	 * @throws BaiduMapLocationFormatException 格式错误抛出此异常
	 */
	public String getCityByLocation(String location) throws BaiduMapLocationFormatException;
	/**
	 * @author yuanhaohe
	 * @param longitudeAndlatitude 经纬度键值对(longitude:精度值,latitude:纬度值)。
	 * @return 百度地图得到的对应城市名称
	 * @throws BaiduMapLocationFormatException 格式错误抛出此异常
	 */
	public String getCityByMap(Map<String,Double> map) throws BaiduMapLocationFormatException;
	/**
	 * @author yuanhaohe
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @return 百度地图得到的对应城市名称
	 * @throws BaiduMapLocationFormatException 格式错误抛出此异常
	 */
	public String getCityByLongitudeAndLatitude(double longitude,double latitude) throws BaiduMapLocationFormatException;
}
