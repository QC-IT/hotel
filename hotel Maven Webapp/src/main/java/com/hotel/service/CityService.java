package com.hotel.service;

import java.util.List;

import com.hotel.models.City;

public interface CityService {
	
	
	public List<City> getHotCityList() throws Exception;
	
	public List<City> getAllCityList()throws Exception;
	
	public int selectCityIdByName(String name) throws Exception;
	
	
}
