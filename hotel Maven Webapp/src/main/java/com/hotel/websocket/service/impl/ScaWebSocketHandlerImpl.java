package com.hotel.websocket.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.hotel.websocket.service.ScaWebSocketHandler;

@Service
public class ScaWebSocketHandlerImpl extends TextWebSocketHandler implements ScaWebSocketHandler{
	private static final String flag="sca_connectionid";
    //全国在线的等待授权用户集合
    private static final Map<String, Map<String,WebSocketSession>> users;
   private static Logger logger=org.slf4j.LoggerFactory.getLogger(ScaWebSocketHandlerImpl.class);
  static{
	  users=new ConcurrentHashMap<String,Map<String,WebSocketSession>>();
  }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String id = getClientId(session);
        logger.debug("websocket请求连接! id="+id);
        if (id != null) {
        	String[] tmp=id.split("_");
        	String hotel_id=tmp[0];
        	String userid=tmp[1];
        	 Map<String,WebSocketSession> map=users.get(hotel_id);
        	 if(map==null){
        		 Map<String,WebSocketSession> userMap=new ConcurrentHashMap<String,WebSocketSession>();
        		 users.put(hotel_id, userMap);
        		 userMap.put(userid,session);
        	 }else{
        		 map.put(userid,session);
        	 }
          
            session.sendMessage(new TextMessage("{\"code\":200,\"msg\":\"success\"}"));
            logger.debug("websocket连接成功!");
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
logger.debug("向客户端发送信息:"+message.toString());
        WebSocketMessage<String> message1 = message;
        try {
            session.sendMessage(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息给指定用户
     * @param clientId  包括酒店ID_用户ID
     * @param message
     * @return 成功true 失败 false
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
    	String[] tmp=clientId.split("_");
    	String hotelid=tmp[0];
    	String userid=tmp[1];
        if (users.get(hotelid) == null||users.get(hotelid).get(userid)==null) return false;
        WebSocketSession session = users.get(hotelid).get(userid);
       logger.debug("sendMessage to " + session);
        if (!session.isOpen()) return false;
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.debug("连接出错");
        String[] tmp=getClientId(session).split("_");
        users.get(tmp[0]).remove(tmp[1]);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.debug("连接已关闭：" + status);
        String[] tmp=getClientId(session).split("_");
        users.get(tmp[0]).remove(tmp[1]);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return 从连接session中获取clientId
     */
    private String getClientId(WebSocketSession session) {
        try {
            String clientId = (String) session.getAttributes().get(flag);
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }
    }