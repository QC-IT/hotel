package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.CityDao;
import com.hotel.models.City;
import com.hotel.service.CityService;

@Service
public class CityServiceImpl implements CityService {
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
		if (name != null && !name.trim().equals("")) {
			Integer result = cityDao.getCityIdByName(name);
			if (result == null) {
				return 0;
			} else {
				return result;
			}
		} else {
			return 0;
		}
	}

	@Transactional
	public String getCodeByCityName(String cityName) throws Exception {
		if (cityName != null && !cityName.trim().equals("")) {
			return cityDao.getCodeByCityName(cityName);
		} else {
			return null;
		}
	}

	@Transactional
	public void insertCityList(List<City> citys) throws Exception {
		if (citys != null && citys.size() != 0) {
			for (City city : citys) {
				if (city.getIshot() == null) {
					city.setIshot("0");
				}
			}
			cityDao.addCityList(citys);
		}

	}

	@Transactional
	public void deleteCityList(List<String> ids) throws Exception {
		if (ids != null && ids.size() != 0) {
			cityDao.deleteCityList(ids);
		}
	}

	@Transactional
	public void insertCity(City city) throws Exception {
		if (city.getIshot() == null) {
			city.setIshot("0");
		}
		cityDao.addCity(city);
	}

	@Transactional
	public void deleteCity(String id) throws Exception {
		if (id != null && !id.trim().equals("")) {
			cityDao.deleteCityById(id);
		}

	}
}
