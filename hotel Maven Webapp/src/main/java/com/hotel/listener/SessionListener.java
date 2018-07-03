package com.hotel.listener;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{
private static AtomicInteger activeUser;

	public void sessionCreated(HttpSessionEvent se) {
		activeUser.getAndIncrement();
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	activeUser.getAndDecrement();
	}
public int getActiveUserNum(){
	return activeUser.intValue();
}

}
