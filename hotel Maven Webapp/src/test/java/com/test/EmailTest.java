package com.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.redis.impl.RedisServiceImpl;

public class EmailTest {


	@Test
	public void test() {
BeanFactory factory=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
RedisServiceImpl service=factory.getBean(RedisServiceImpl.class);
service.set("map", 111);
System.out.println(service.get("map"));
	}
}
