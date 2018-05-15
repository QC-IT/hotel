package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.api.service.BaiduMapService;
import com.hotel.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private BaiduMapService baiduMapService;
@RequestMapping(value="test.json",method=RequestMethod.GET,produces="application/json;charset=utf-8")

	public @ResponseBody String test(){
	try {
		int c=testService.test();
		String city=baiduMapService.getCityByLongitudeAndLatitude(123.0, 45);
		System.out.println(city);
		System.out.println(c);
	} catch (Exception e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
		return "{\"code\":1}";
	}
}
