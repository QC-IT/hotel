package com.hotel.email.service;

public interface EmailService {

	public boolean sendSimpleTextEmailTo(String to,String content,String subject);
	public boolean sendHtmlTextEmailTo(String to,String html,String subject);
	
}
