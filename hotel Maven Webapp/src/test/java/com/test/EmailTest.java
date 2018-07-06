package com.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.controller.HotelItemsController;
import com.hotel.service.QuartzService;

public class EmailTest {


	public static void main(String[] args) throws Exception{
		 BeanFactory factory	=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
		 QuartzService service=factory.getBean(QuartzService.class);
service.autoSendLogEmail();

	}
}
