package com.hotel.websocket.service;

import org.springframework.web.socket.TextMessage;

public interface ScaWebSocketHandler {
	public boolean sendMessageToUser(String clientId, TextMessage message);

}
