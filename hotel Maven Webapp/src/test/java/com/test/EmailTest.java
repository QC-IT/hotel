package com.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.controller.HotelItemsController;

public class EmailTest {


	public static void main(String[] args) throws Exception{
		 BeanFactory factory	=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
HotelItemsController service=factory.getBean(HotelItemsController.class);
Map<String,String> param=new HashMap<String, String>();
param.put("hid", "00033d92a65a49d0ad3a230d80d4b8df");
param.put("type", "0");
System.out.println(service.getItemsByHidAndType(param));

	}
}
