package com.hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	private final Logger logger=LoggerFactory.getLogger(HotelItemsController.class);
	/**
	 * 获取当前酒店的全部服务
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
			logger.debug(json.toJSONString());
			return json.toJSONString();
		} catch (Exception e) {
			logger.debug("{\"code\":\"500\",\"msg\":\"未知错误\"}");
			return "{\"code\":\"500\",\"msg\":\"未知错误\"}";
		}
	}
	
	/**
	 * 添加服务
	 * @param map 
	 *        beginTime createTime endTime hid picUrl detailContent items
	 * @return
	 */
	@RequestMapping(value="/addService.json",method=RequestMethod.POST,produces="application/json;charset=utf8")
	public @ResponseBody
	String insertItems(MultipartFile file,
			String beginTime,
			String createTime,
			String endTime,
			String hid,
			String items,
			String detailContent){
		SimpleDateFormat format=new SimpleDateFormat(dataFormat);
		Items item=new Items();
		item.setHid(hid);
		try {
			item.setBeginTime(format.parse(beginTime));
		} catch (ParseException e) {
			logger.debug( "{\"code\":501,\"msg\":\"日期格式有误\"}");
			return "{\"code\":501,\"msg\":\"日期格式有误\"}";
		}
		try {
			item.setCreateTime(format.parse(createTime));
		} catch (ParseException e) {
			logger.debug("{\"code\":501,\"msg\":\"日期格式有误\"}");
			return "{\"code\":501,\"msg\":\"日期格式有误\"}";
		}
		item.setDetailContent(detailContent);
		try {
			item.setEndTime(format.parse(endTime));
		} catch (ParseException e) {
			logger.debug("{\"code\":501,\"msg\":\"日期格式有误\"}");
			return "{\"code\":501,\"msg\":\"日期格式有误\"}";
		}
		item.setItem(items);
		try {
			hotelItemsService.insertItems(item);
			if(file!=null){
				hotelItemsService.updateServicePic(file, String.valueOf(item.getId()));
			}
			logger.debug("{\"code\":200,\"msg\":\"success\"}");
			return "{\"code\":200,\"msg\":\"success\"}";
		} catch (Exception e) {
			logger.debug("{\"code\":500,\"msg\":\"图片上传失败\"}");
	return "{\"code\":500,\"msg\":\"图片上传失败\"}";
		}

		
	}
}
