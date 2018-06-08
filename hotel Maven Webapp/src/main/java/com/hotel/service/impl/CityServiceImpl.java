package com.hotel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hotel.dao.CityDao;
import com.hotel.models.City;
import com.hotel.service.CityService;

@Service
public class CityServiceImpl implements CityService{
@Autowired
private CityDao cityDao;
@Transactional
	public List<City> getHotCityList() throws Exception {
		return cityDao.getHotCity();
	}
@Transactional
	public List<City> getAllCityList() throws Exception {
		return cityDao.getAllCity();
	}
@Transactional
public int selectCityIdByName(String name) throws Exception {
	return cityDao.getCityIdByName(name);
}
@Transactional
public String getCodeByCityName(String cityName) throws Exception {
	
	return cityDao.getCodeByCityName(cityName);
}
@Transactional
public void insertCityList(List<City> citys) throws Exception{
cityDao.addCityList(citys);
	
}

@Transactional
public void deleteCityList(List<String> ids) throws Exception{
	cityDao.deleteCityList(ids);
}
@Transactional
public void insertCity(City city) throws Exception {
	if(city.getIshot()==null){
		city.setIshot("0");
	}
	cityDao.addCity(city);
}
@Transactional
public void deleteCity(String id) throws Exception {
	cityDao.deleteCityById(id);
	
}
}
