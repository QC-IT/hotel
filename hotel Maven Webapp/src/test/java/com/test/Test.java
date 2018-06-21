package com.test;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.service.HotelService;

public class Test {
	private BeanFactory factory=null;
	
	@Before
	public void before(){
		factory=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
	}
	
@org.junit.Test
public void test() throws Exception{
	HotelService service=factory.getBean(HotelService.class);

}
}
 