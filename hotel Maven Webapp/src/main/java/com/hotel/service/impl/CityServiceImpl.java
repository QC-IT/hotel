package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.CityDao;
import com.hotel.models.City;
import com.hotel.redis.RedisService;
import com.hotel.service.CityService;


/**
 * @author yuanhaohe
 *
 */
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;
	@Autowired
	private RedisService redisService;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<City> getHotCityList() throws Exception {
		//redis键为hotCity得到热点城市
		if (redisService.exists("hotCity")) {
			return (List<City>) redisService.get("hotCity");
		} else {
			List<City> list = cityDao.getHotCity();
			redisService.set("hotCity", list, 24 * 60 * 60l);
			return list;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<City> getAllCityList() throws Exception {
		//redis键为allCity得到全部城市
		if (redisService.exists("allCity")) {
			return (List<City>) redisService.get("allCity");
		} else {
			List<City> list = cityDao.getAllCity();
			redisService.set("allCity", list, 7 * 24 * 60 * 60l);
			return list;
		}

	}

	@Transactional
	public int selectCityIdByName(String name) throws Exception {
		if (name != null && !name.trim().equals("")) {
			Integer result = null;
			//redis键为城市名字-id 得到城市id
			if (redisService.exists(name + "-id")) {
				result = (Integer) redisService.get(name + "-id");
			} else {
				result = cityDao.getCityIdByName(name);
				redisService.set(name + "-id", result, 7 * 24 * 60 * 60l);
			}
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
			//redis键为城市名字-code 得到城市code
			String result = null;
			if (redisService.exists(cityName + "-code")) {
				return (String) redisService.get(cityName + "-code");
			} else {
				result = cityDao.getCodeByCityName(cityName);
				redisService.set(cityName + "-code", result, 7 * 24 * 60 * 60l);
				return result;
			}

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
					if (redisService.exists("allCity")) {
						redisService.remove("allCity");
					}
					if (redisService.exists("hotCity")) {
						redisService.remove("hotCity");
					}
				}
			}
			cityDao.addCityList(citys);
		}

	}

	@Transactional
	public void deleteCityList(List<String> ids) throws Exception {
		if (ids != null && ids.size() != 0) {
			cityDao.deleteCityList(ids);
			if (redisService.exists("allCity")) {
				redisService.remove("allCity");
			}
			if (redisService.exists("hotCity")) {
				redisService.remove("hotCity");
			}
		}
	}

	@Transactional
	public void insertCity(City city) throws Exception {
		if (city.getIshot() == null) {
			city.setIshot("0");
			if (redisService.exists("allCity")) {
				redisService.remove("allCity");
			}
			if (redisService.exists("hotCity")) {
				redisService.remove("hotCity");
			}
		}
		cityDao.addCity(city);
	}

	@Transactional
	public void deleteCity(String id) throws Exception {
		if (id != null && !id.trim().equals("")) {
			cityDao.deleteCityById(id);
			if (redisService.exists("allCity")) {
				redisService.remove("allCity");
			}
			if (redisService.exists("hotCity")) {
				redisService.remove("hotCity");
			}
		}

	}
}
