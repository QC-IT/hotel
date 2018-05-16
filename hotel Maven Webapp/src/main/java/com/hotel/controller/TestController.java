package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.api.service.BaiduMapService;
import com.hotel.jms.producer.TestProducer;
import com.hotel.redis.RedisService;
import com.hotel.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private BaiduMapService baiduMapService;
	@Autowired
	private TestProducer producer;
	@Autowired
	private RedisService redisService;
@RequestMapping(value="test.json",method=RequestMethod.GET,produces="application/json;charset=utf-8")

	public @ResponseBody String test(){
	try {
		int c=testService.test();
		String city=baiduMapService.getCityByLongitudeAndLatitude(123.0, 45);
		producer.sendMessage(city);
		System.out.println(city);
		System.out.println(c);
		System.out.println("start redis-----");
		redisService.set("username", "123456");
		System.out.println(redisService.get("username"));
	} catch (Exception e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
		return "{\"code\":1}";
	}
}
