package com.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotel.models.Hotel;
import com.hotel.service.HotelService;
import com.hotel.util.DistanceUtil;

@Controller
@RequestMapping("/hotel")
public class HotelServiceController {
	private static final Logger logger = LoggerFactory.getLogger(HotelServiceController.class);
	@Autowired
	private HotelService hotelService;

	@RequestMapping(value="searchHotel.json",produces="application/json;charset=utf8")
	public @ResponseBody String searchHotel(String name, String longitude, String latitude) {
		List<Hotel> list = hotelService.getSearchResult(name, longitude, latitude);
		JSONObject json = new JSONObject();
		json.put("code", "200");
		List<Hotel> result = new ArrayList<Hotel>(10);
		for (int i = 0; i < list.size() && i < 10; i++) {
			result.add(i, list.get(i));
		}
		list.forEach(h -> {
			h.setDistance(DistanceUtil.getDistance(latitude, longitude, h.getLatitude(), h.getLongitude()));
		});
		logger.debug("search hotels list size :" + result.size());
		json.put("hotels", result);
		return json.toJSONString();
	}

	@RequestMapping(value="socketContent.json",produces="application/json;charset=utf8")
	public @ResponseBody String getSocketContent(HttpSession session, @RequestParam("socketId") String socketId) {
		session.setAttribute("sca_connectionid", socketId);
		return "{\"code\":200}";
	}

	@RequestMapping(value="getRecomHotelListByCityName.json",produces="application/json;charset=utf8")
	public @ResponseBody String getRecomHotelListByCityName(String cityName, String longitude, String latitude) {
		List<Hotel> list = hotelService.getRecomHotelListByCityName(cityName);
		list.forEach(h -> {
			h.setDistance(DistanceUtil.getDistance(latitude, longitude, h.getLatitude(), h.getLongitude()));
		});
		JSONObject json = new JSONObject();
		json.put("code", "200");
		json.put("hotels", list);
		return json.toJSONString();
	}

	@RequestMapping(value="getRecomHotelListByCityCode.json",produces="application/json;charset=utf8")
	public @ResponseBody String getRecomHotelListByCityCode(String cityCode, String longitude, String latitude) {
		List<Hotel> list = hotelService.getRecomHotelListByCityCode(cityCode);
		list.forEach(h -> {
			h.setDistance(DistanceUtil.getDistance(latitude, longitude, h.getLatitude(), h.getLongitude()));
		});
		JSONObject json = new JSONObject();
		json.put("code", "200");
		json.put("hotels", list);
		return json.toJSONString();
	}
}
