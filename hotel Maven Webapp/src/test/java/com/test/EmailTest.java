package com.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.email.service.EmailService;

public class EmailTest {
	private BeanFactory factory;
	@Before
public void before(){
	factory=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
		
}
	
	@Test
	public void test(){
		EmailService es=factory.getBean(EmailService.class);
		es.sendHtmlTextEmailTo("523076835@qq.com", "<h1> hello </h1>", "1+超级会员");
	}
}
 