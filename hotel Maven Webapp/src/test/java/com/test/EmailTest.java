package com.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.service.QuartzService;

public class EmailTest {


	@Test
	public void test() {
BeanFactory factory=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
QuartzService service=factory.getBean(QuartzService.class);
service.autoComment();
	}
}
