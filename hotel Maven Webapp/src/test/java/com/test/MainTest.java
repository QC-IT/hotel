package com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.controller.HotelController;

public class MainTest {
private BeanFactory factory;

@Before
public void before(){
	factory=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
}
@Test
public void test() throws Exception{
	HotelController controller=factory.getBean(HotelController.class);
	Map<String,String> map=new HashMap<String,String>();
	map.put("latitude", "46");
	map.put("longitude", "125");
	map.put("name", "春天");
	System.out.println(controller.searchHotel(map));
}

}
