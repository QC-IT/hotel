package com.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotel.models.User;
import com.hotel.service.UserService;

public class EmailTest {
private BeanFactory factory;
@Before
public void before(){
	factory=new ClassPathXmlApplicationContext("classpath:applicationContext-beans.xml");
}
	@Test
	public void test(){
		UserService us=factory.getBean(UserService.class);
		User user =new User();
		user.setOpenID("asda12342");
		user.setHeadPic("user_head_pic/sadasdada-dadsw1sdas-dasdwqeds-22.jpg");
		us.updateUserInfo(user);
	}
}
