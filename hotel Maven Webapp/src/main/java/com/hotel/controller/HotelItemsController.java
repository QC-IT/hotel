package com.hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotel.models.Items;
import com.hotel.service.HotelItemsService;

@Controller
@RequestMapping("/hotel")
public class HotelItemsController {
	@Autowired
	private HotelItemsService hotelItemsService;
	@Value("${system_dataformat}")
	private String dataFormat;
	
	/**
	 * 获取当前城市的服务
	 * @param map
	 * @return
	 */
	@RequestMapping(value="getItemsThisHotel.json",produces="application/json;charset=utf8")
	public @ResponseBody
	String getItemsThisHotel(@RequestBody Map<String,String> map){
		String hotelId=map.get("hotelId"); 
		List<Items> list;
		try {
			list = hotelItemsService.getHotelItems(hotelId);
			JSONObject json=new JSONObject();
			json.put("code", 200);
			
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("services", list);
			json.put("data", data);
			return json.toJSONString();
		} catch (Exception e) {
			return "{\"code\":\"500\",\"msg\":\"未知错误\"}";
		}
	}
	
	/**
	 * 添加服务
	 * @param map 
	 *        beginTime createTime endTime hid picUrl detailContent items
	 * @return
	 */
	@RequestMapping(value="addService.json",produces="application/json;charset=utf8")
	public @ResponseBody
	String insertItems(@RequestBody Map<String,String> map){
		SimpleDateFormat format=new SimpleDateFormat(dataFormat);
		String beginTime=map.get("beginTime");
		String createTime=map.get("createTime");
		String endTime=map.get("endTime");
		String picUrl=map.get("picUrl");
		Items items=new Items();
		items.setPicUrl(picUrl);
		items.setHid(map.get("hid"));
		try {
			items.setBeginTime(format.parse(beginTime));
		} catch (ParseException e) {
			return "{\"code\":501,\"msg\":\"日期格式有误\"}";
		}
		try {
			items.setCreateTime(format.parse(createTime));
		} catch (ParseException e) {
			return "{\"code\":501,\"msg\":\"日期格式有误\"}";
		}
		items.setDetailContent(map.get("detailContent"));
		try {
			items.setEndTime(format.parse(endTime));
		} catch (ParseException e) {
			return "{\"code\":501,\"msg\":\"日期格式有误\"}";
		}
		items.setItem(map.get("items"));
		try {
			hotelItemsService.insertItems(items);
			return "{\"code\":200,\"msg\":\"success\"}";
		} catch (Exception e) {
	return "{\"code\":500,\"msg\":\"未知错误\"}";
		}

		
	}
}
