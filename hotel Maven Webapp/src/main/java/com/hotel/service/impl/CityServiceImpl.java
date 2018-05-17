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

@Override
public int selectCityIdByName(String name) throws Exception {

	return cityDao.getCityIdByName(name);
}

}
