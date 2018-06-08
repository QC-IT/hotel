package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.api.service.BaiduMapService;
import com.hotel.dao.HotelDao;
import com.hotel.models.Hotel;
import com.hotel.service.CityService;
import com.hotel.service.HotelService;
import com.hotel.util.DistanceUtil;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private CityService cityService;
	@Autowired
	private BaiduMapService baiduMapService;
	
	private final static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Transactional
	public List<Hotel> getSearchResult(String name, String longitude, String latitude) throws Exception {
		List<Hotel> list = hotelDao.getHotelInfoByCityCodeAndName("'%" + name + "%'");
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
		logger.debug("根据" + name + "模糊查找到:" + list.size() + "条记录");

		return list;
	}
@Transactional
	public List<Hotel> getRecomHotelListByCityCode(String cityCode) throws Exception {
		List<Hotel> list = hotelDao.getRecommendHotelByCity(cityCode);
		logger.debug("通过" + cityCode + "查找到" + list.size() + "个酒店");
		list = list.parallelStream().sorted((o1, o2) -> {
			if (o1.getRecommendOrderCode() > o2.getRecommendOrderCode()) {
				return 1;
			} else if (o1.getRecommendOrderCode() < o2.getRecommendOrderCode()) {
				return -1;
			} else
				return 0;
		}).collect(Collectors.toList());
		return list;
	}
@Transactional
	public List<Hotel> getRecomHotelListByCityName(String name) throws Exception {
		String cityCode = null;
		try {
			cityCode = cityService.getCodeByCityName(name);
		} catch (Exception e) {
			logger.info("获取当前城市推荐酒店列表失败！原因：找不到对应的城市");
		}

		if (cityCode != null) {
			return this.getRecomHotelListByCityCode(cityCode);
		}
		return null;

	}
@Transactional
	@Override
	public boolean closeHotel(String id) throws Exception {
		try {
			hotelDao.changeHotelStateById(id, "0");
			logger.debug("将id为" + id + "的酒店状态变为关闭");
			return true;
		} catch (Exception e) {
			return false;
		}

	}

@Transactional
	@Override
	public boolean openHotel(String id) throws Exception {
		try {
			hotelDao.changeHotelStateById(id, "1");
			logger.debug("将id为" + id + "的酒店状态变为营业");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
@Transactional
	@Override
	public boolean restHotel(String id) throws Exception {
		try {
			logger.debug("将id为" + id + "的酒店状态变为未营业");
			hotelDao.changeHotelStateById(id, "2");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

@Transactional
	@Override
	public boolean updateHotelBaseInfo(Hotel hotel) throws Exception {
		try {
			hotelDao.updateHotelBaseInfo(hotel);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

@Transactional
	@Override
	public boolean insertHotelBaseInfo(Hotel hotel) throws Exception {
		try {
			hotelDao.insertHotelBaseInfo(hotel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

@Transactional
	@Override
	public List<Hotel> getNearHotelLimit(String lat, String lng, String index, String amount, HttpSession session)
			throws Exception {
		@SuppressWarnings("unchecked")
		List<Hotel> hotels=(List<Hotel>) session.getAttribute("nearHotel");
		if(hotels==null){
		String city=baiduMapService.getCityByLocation(lat+","+lng);
		String cityCode=cityService.getCodeByCityName(city.replace("市", ""));
		List<Hotel> list=hotelDao.selectHotelByCityCode(cityCode);
		list.forEach(s->{s.setDistance(DistanceUtil.getDistance(s.getLatitude(), s.getLongitude(), lat, lng));});
		list=list.parallelStream().sorted(new Comparator<Hotel>() {
			@Override
			public int compare(Hotel o1, Hotel o2) {
				if(o1.getDistance()>o2.getDistance()){
					return 1;
				}else if(o1.getDistance()<o2.getDistance()){
					return -1;
				}else{
				return 0;
				}
			}
		}).collect(Collectors.toList());
		session.setAttribute("nearHotel", hotels);
		List<Hotel> result=new ArrayList<Hotel>();
		for(int i=Integer.parseInt(index);i<Integer.parseInt(index)+Integer.parseInt(amount);i++){
			result.add(list.get(i));
		}
		return result;
		}else{
			List<Hotel> result=new ArrayList<Hotel>();
			for(int i=Integer.parseInt(index);i<Integer.parseInt(index)+Integer.parseInt(amount);i++){
				result.add(hotels.get(i));
			}
			return result;
		}
	}
}
