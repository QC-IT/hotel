package com.hotel.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotel.api.service.BaiduMapService;
import com.hotel.exceptions.BaiduMapLocationFormatException;
import com.hotel.util.HttpClientUtil;
/**
 * 
 * @author yuanhaohe
 * 百度地图服务
 */
@Service
public class BaiduMapServiceImpl implements BaiduMapService{
@Value("${Baidu_map_ak}")
private String baidu_ak;
	/**
	 * @author yuanhaohe
	 * @param location 经纬度（如35.658651,139.745415）纬度在前经度在后，中间以","隔开 。
	 * @return 百度地图得到的对应城市名称
	 * @throws BaiduMapLocationFormatException 格式错误抛出此异常
	 */
	public String getCityByLocation(String location) throws BaiduMapLocationFormatException{
		String[] s=location.split(",");
		double d1=Double.parseDouble(s[0]);
		double d2=Double.parseDouble(s[1]);
		if(d1>90||d2>360){
			throw new BaiduMapLocationFormatException("经纬度格式有误");
		}else {
			String result=HttpClientUtil.sendGet("http://api.map.baidu.com/geocoder/v2/", "callback=renderReverse&location="+location+"&output=json&pois=1&ak="+baidu_ak);
			result=result.substring(result.indexOf('(')+1, result.length()-1);
			JSONObject json=JSON.parseObject(result);
		    String city=((JSONObject)((JSONObject)json.get("result")).get("addressComponent")).getString("city");
			return city;
		}
	
	}
	/**
	 * @author yuanhaohe
	 * @param longitudeAndlatitude 经纬度键值对(longitude:精度值,latitude:纬度值)。
	 * @return 百度地图得到的对应城市名称
	 * @throws BaiduMapLocationFormatException 格式错误抛出此异常
	 */
	public String getCityByMap(Map<String, Double> longitudeAndlatitude ) throws BaiduMapLocationFormatException{
		double longitude=longitudeAndlatitude.get("longitude");
		double latitude=longitudeAndlatitude.get("latitude");
		return getCityByLocation(latitude+","+longitude);
	}
	
	/**
	 * @author yuanhaohe
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @return 百度地图得到的对应城市名称
	 * @throws BaiduMapLocationFormatException 格式错误抛出此异常
	 */
	public String getCityByLongitudeAndLatitude(double longitude, double latitude)  throws BaiduMapLocationFormatException{
		// TODO 自动生成的方法存根
		return getCityByLocation(latitude+","+longitude);
	}

}
