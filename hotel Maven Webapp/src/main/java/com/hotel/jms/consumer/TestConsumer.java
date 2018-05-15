package com.hotel.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TestConsumer implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("接收消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
