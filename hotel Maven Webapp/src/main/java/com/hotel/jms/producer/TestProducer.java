package com.hotel.jms.producer;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
@Service
public class TestProducer {
	@Resource(name="jmsQueueTemplate")
private JmsTemplate jmsTemplate;
	public void  sendMessage(String city){
		MessageCreator creator = new MessageCreator() {  
            @Override  
            public Message createMessage(Session session) throws JMSException {  
                TextMessage message =session.createTextMessage();  
                message.setText(city);  
                return message;  
            }  
        };  
        jmsTemplate.send(new ActiveMQQueue("spring-activemq-queue"), creator);  
	}
}
