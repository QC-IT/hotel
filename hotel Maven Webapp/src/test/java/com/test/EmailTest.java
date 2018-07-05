package com.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.api.service.BaiduMapService;
import com.hotel.models.ServiceJoin;
import com.hotel.service.CityService;
import com.hotel.service.UserJoinService;

public class EmailTest {


	public static void main(String[] args) throws Exception{
		 BeanFactory factory	=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
CityService service=factory.getBean(CityService.class);

	}
}
