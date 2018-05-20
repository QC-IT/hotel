package com.hotel.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hotel.redis.RedisService;
import com.hotel.redis.impl.RedisServiceImpl;

public class SessionListener implements HttpSessionListener{
private static RedisService redisService=new RedisServiceImpl();

	public void sessionCreated(HttpSessionEvent se) {
		
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		String id=se.getSession().getId();
		if(redisService.exists(id)){
		redisService.remove();
		}
	}

}
