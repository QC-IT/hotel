package com.hotel.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.api.service.BaiduMapService;
import com.hotel.dao.HotelDao;
import com.hotel.exceptions.BaiduMapLocationFormatException;
import com.hotel.models.Hotel;
import com.hotel.service.CityService;
import com.hotel.service.HotelService;
import com.hotel.util.DistanceUtil;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private BaiduMapService baiduMapService;
	@Autowired
	private CityService cityService;
private final static Logger logger=LoggerFactory.getLogger(HotelServiceImpl.class);
	@Override
	@Transactional
	public List<Hotel> getSearchResult(String name, String longitude, String latitude) {
		String city = null;
		try {
			city = baiduMapService.getCityByLocation(latitude + "," + longitude);
			city=city.substring(0,city.length()-1);
		} catch (BaiduMapLocationFormatException e) {
			logger.info("未能正常得到百度api中地理位置逆向的城市名称");
		}
		String cityCode = null;
		try {
			cityCode = cityService.getCodeByCityName(city);
		} catch (Exception e) {
			logger.info("未能正常得到数据库中的城市code");
		}
		List<Hotel> list = hotelDao.getHotelInfoByCityCodeAndName(cityCode, "'%" + name + "%'");
		list.forEach((hotel) -> {
			hotel.setDistance(DistanceUtil.getDistance(hotel.getLatitude(), hotel.getLongitude(), latitude, longitude));
		});
		list = list.parallelStream().sorted(new Comparator<Hotel>() {
			@Override
			public int compare(Hotel o1, Hotel o2) {
				if (o1.getDistance() > o2.getDistance()) {
					return 1;
				} else if (o1.getDistance() < o2.getDistance()) {
					return -1;
				} else {
					return 0;
				}
			}
		}).collect(Collectors.toList());

		return list;
	}

	
	public List<Hotel> getRecomHotelListByCityCode(String cityCode) {
List<Hotel> list=hotelDao.getRecommendHotelByCity(cityCode);
list=list.parallelStream().sorted((o1,o2)->{
	if(o1.getRecommendOrderCode()>o2.getRecommendOrderCode()){return 1;}
	else if(o1.getRecommendOrderCode()<o2.getRecommendOrderCode()){return -1;}
	else return 0;
	}
).collect(Collectors.toList());
		return list;
	}

	public List<Hotel> getRecomHotelListByCityName(String name){
		String cityCode=null;
		try {
			 cityCode = cityService.getCodeByCityName(name);
		} catch (Exception e) {
		logger.info("获取当前城市推荐酒店列表失败！原因：找不到对应的城市");
		}

		if(cityCode!=null){
			return this.getRecomHotelListByCityCode(cityCode);
		}
		return null;
	
		
	}
}
