package com.hotel.api.service;

import java.util.Map;

import com.hotel.exceptions.BaiduMapLocationFormatException;

public interface BaiduMapService {

	
	public String getCityByLocation(String location) throws BaiduMapLocationFormatException;
	
	public String getCityByMap(Map<String,Double> map) throws BaiduMapLocationFormatException;
	
	public String getCityByLongitudeAndLatitude(double longitude,double latitude) throws BaiduMapLocationFormatException;
}
