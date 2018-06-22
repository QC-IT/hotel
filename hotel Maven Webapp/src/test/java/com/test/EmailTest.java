package com.test;

import org.junit.Test;

import com.hotel.util.HttpClientUtil;

public class EmailTest {


	@Test
	public void test() {
String json=HttpClientUtil.sendPostJSON("http://localhost:8080/hotel/hotel/searchHotel.json", "{\"latitude\":\"46.2\",\"longitude\":\"123\",\"name\":\"春天\"}");
System.out.println(json);
	}
}
