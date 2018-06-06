package com.test;

import com.hotel.util.HttpClientUtil;

public class Test {
	
@org.junit.Test
public void test(){

	String s=HttpClientUtil.sendPost("http://localhost:8080/hotel/city/findHotCity.json", null);
	System.out.println(s);
	
}
}
