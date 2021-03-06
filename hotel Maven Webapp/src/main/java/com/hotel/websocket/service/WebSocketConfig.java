package com.hotel.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//@Configuration
//@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Autowired
private WebSocketHandler handler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	//scaConnection.json是webSocket连接的地址
        registry.addHandler(handler, "/scaConnection.json").addInterceptors(new ScaWebSocketInterceptor());
    }
}