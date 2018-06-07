package com.hotel.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotel.websocket.service.ScaWebSocketHandler;

public class ScanConsumer implements MessageListener{
	
	@Autowired
	private ScaWebSocketHandler scaWebSocketHandler;
	@Override
	public void onMessage(Message message) {
		try {
		String msg=((TextMessage)message).getText();
		JSONObject json=JSON.parseObject(msg);
		String code=json.getString("code");
		String clientId=json.getString("clientId");
		scaWebSocketHandler.sendMessageToUser(clientId,new org.springframework.web.socket.TextMessage("{\"code\":"+code+"}"));
		} catch (JMSException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
