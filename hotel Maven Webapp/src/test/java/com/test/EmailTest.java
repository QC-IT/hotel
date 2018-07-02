package com.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.controller.CityController;

public class EmailTest {
private BeanFactory factory;
@Before
public void before(){
	factory=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
}
	
}
