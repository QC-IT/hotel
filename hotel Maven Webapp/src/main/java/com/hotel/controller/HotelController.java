package com.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotel.models.Hotel;
import com.hotel.service.HotelService;
import com.hotel.util.DistanceUtil;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
	@Autowired
	private HotelService hotelService;
	
/**
 * 通过查询名称模糊搜索酒店信息
 * @param map 
 *        name longitude latitude
 * @return
 */
	@RequestMapping(value = "searchHotel.json",produces = "application/json;charset=utf8")
	public @ResponseBody String searchHotel(@RequestBody Map<String, String> map) {
		String name = map.get("name");
		String longitude = map.get("longitude");
		String latitude = map.get("latitude");
		List<Hotel> list = null;
		try {
			list = hotelService.getSearchResult(name, longitude, latitude);
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
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("hotels", result);
			json.put("data", data);
			return json.toJSONString();
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"服务器出现未知异常\"}";
		}
	
	}
	
/**
 * websocket连接会话的id 城市code_用户ID
 * @param session
 * @param map 
 *        socketId
 * @return
 */
	@RequestMapping(value = "socketContent.json",produces = "application/json;charset=utf8")
	public @ResponseBody String getSocketContent(HttpSession session, @RequestBody Map<String, String> map) {
		String socketId = map.get("socketId");
		if(socketId!=null){
		session.setAttribute("sca_connectionid", socketId);
		return "{\"code\":200,\"msg\":\"success\"}";
		}else{
			return "{\"code\":500,\"msg\":\"没有找到对应的值\"}";
		}

	}
	
/**
 * 通过城市名字获取推荐酒店
 * @param map
 *        cityName longitude latitude
 * @return
 */
	@RequestMapping(value = "getRecomHotelListByCityName.json", produces = "application/json;charset=utf8")
	public @ResponseBody String getRecomHotelListByCityName(@RequestBody Map<String, String> map) {
		String cityName = map.get("cityName");
		String longitude = map.get("longitude");
		String latitude = map.get("latitude");
		List<Hotel> list = null;
		try {
			list = hotelService.getRecomHotelListByCityName(cityName);
			list.forEach(h -> {
				h.setDistance(DistanceUtil.getDistance(latitude, longitude, h.getLatitude(), h.getLongitude()));
			});
			JSONObject json = new JSONObject();
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("hotels", list);
			json.put("code", "200");
			json.put("data", data);
			return json.toJSONString();
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"服务器出现未知异常\"}";
		}
	

	}

	/**
	 * 通过cityCode获取推荐酒店
	 * @param map
	 *        cityCode longitude latitude
	 * @return
	 */
	@RequestMapping(value = "getRecomHotelListByCityCode.json",produces = "application/json;charset=utf8")
	public @ResponseBody String getRecomHotelListByCityCode(@RequestBody Map<String, String> map) {
		String cityCode = map.get("cityCode");
		String longitude = map.get("longitude");
		String latitude = map.get("latitude");
		List<Hotel> list;
		try {
			list = hotelService.getRecomHotelListByCityCode(cityCode);
			list.forEach(h -> {
				h.setDistance(DistanceUtil.getDistance(latitude, longitude, h.getLatitude(), h.getLongitude()));
			});
			JSONObject json = new JSONObject();
			json.put("code", "200");
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("hotels", list);
			json.put("data", data);
			return json.toJSONString();
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"服务器出现未知异常\"}";
		}

	}
/**
 * 改变酒店状态 1营业 0关闭 2未营业
 * @param map
 *        state  id
 * @return
 */
	//在写酒店方小程序时候这里要做权限判断 state 402
	@RequestMapping(value = "changeHotelState.json", method=RequestMethod.POST,produces = "application/json;charset=utf8")
	public @ResponseBody String changeHotelState(@RequestBody Map<String, String> map) {
		String state = map.get("state");
		String id = map.get("id");
		try {
			if (state.equals(HotelService.HOTEL_CLOSE)) {
				hotelService.closeHotel(id);
			} else if (state.equals(HotelService.HOTEL_OPEN)) {
				hotelService.openHotel(id);
			} else if (state.equals(HotelService.HOTEL_REST)) {
				hotelService.restHotel(id);
			}
			return "{\"code\":200,\"msg\":\"success\"}";
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"服务器出现未知异常\"}";
		}
	}
	
/**
 * 更新酒店信息
 * @param hotel
 * @return
 */
	//在写酒店方小程序时候这里要做权限判断 state 402
	@RequestMapping(value = "updateHotelInfo.json", produces = "application/json;charset=utf8")
	public @ResponseBody String updateHotelInfo(@RequestBody Hotel hotel) {
		try {
			boolean flag = hotelService.updateHotelBaseInfo(hotel);
			if (flag) {
				return "{\"code\":200,\"msg\":\"success\"}";
			} else {
				return "{\"code\":500,\"msg\":\"未知原因更新失败\"}";
			}
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"未知原因更新失败\"}";
		}
	}
	
/**
 * 增加酒店信息
 * @param hotel
 * @return
 */
	//在写酒店方小程序时候这里要做权限判断 state 402
	@RequestMapping(value = "addHotelInfo.json", produces = "application/json;charset=utf8")
	public @ResponseBody String insertHotelBaseInfo(@RequestBody Hotel hotel) {
		try {
			boolean flag = hotelService.insertHotelBaseInfo(hotel);
			if (flag) {
				return "{\"code\":200,\"msg\":\"success\"}";
			} else {
				return "{\"code\":500,\"msg\":\"未知原因添加失败\"}";
			}
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"未知原因添加失败\"}";
		}
	}
	
/**
 * 分页获取最近酒店信息
 * @param session
 * @param map
 *      latitude longitude index amount
 * @return
 */
	@RequestMapping(value="getNearHotelLimit.json",produces="application/json;charset=utf8")
	public @ResponseBody 
	String getNearHotelLimit(HttpSession session,@RequestBody Map<String,String> map){

		try {
			List<Hotel> list=hotelService.getNearHotelLimit(map.get("latitude"), map.get("longitude"), map.get("index"), map.get("amount"), session);
			JSONObject json=new JSONObject();
			json.put("code", 200);
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("nearHotel", list);
json.put("data", data);
return json.toJSONString();
		} catch (Exception e) {
		return "{\"code\":500,\"msg\":\"未知错误\"}";
		}
	}

}
