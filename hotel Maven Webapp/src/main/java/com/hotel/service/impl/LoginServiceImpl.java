package com.hotel.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.UserDao;
import com.hotel.models.User;
import com.hotel.service.LoginService;
import com.hotel.util.HttpClientUtil;
@Service
public class LoginServiceImpl implements LoginService{
@Value("${WeChat_appId}")
private String appId;
@Value("${weChat_appSecret}")
private String appSecret;
@Autowired
private UserDao userDao;
	public String getWeChatOpenIdByCode(String code) {
		String openId=HttpClientUtil.httpsRequest("https://api.weixin.qq.com/sns/jscode2session?appid="+appId+ "&secret="+appSecret+"&js_code="+ code + "&grant_type=authorization_code", "GET", null);
		return openId;
	}

	@Transactional
	public User loginUser(String openId,String latitude,String longitude,String nickName,HttpSession session) {
		String weChatId=(String) session.getAttribute("openId");
		//若session中没有用户分俩种情况 1是用户没有注册 2是用户已经注册没有登录
		if(weChatId==null){
		int count=userDao.selectCountUserByOpenId(openId);
		//若用户没有注册 那就先注册
		if(count==0){
			String uuid=UUID.randomUUID().toString();
			uuid=uuid.replace("-", "");
			//向数据库中插入openid、id和默认用户名
			userDao.insertUser(openId, uuid, nickName);
			userDao.updateUserGpsMsg(openId, latitude, longitude);
			//得到数据库中的user对象返回给前台
			User user=userDao.selectUserInfoByOpenId(openId);
//记录登录状态 用户登录状态由openid维护，但是必须要传入id
		if(user.getState()==1){
			session.setAttribute("openId", user.getOpenID());
			session.setAttribute("id", user.getId());
		}
			return user;
		}else{
			//如果已经注册 那么更新GPS并查找出返回给前端
			userDao.updateUserGpsMsg(openId, latitude, longitude); 	
			User user=userDao.selectUserInfoByOpenId(openId);
			//记录登录状态 用户登录状态由openid维护，但是必须要传入id
			if(user.getState()==1){
				session.setAttribute("openId", user.getOpenID());
				session.setAttribute("id", user.getId());
			}
			return user;
		}
		
	}else{
		//如果已经注册 那么更新GPS并查找出返回给前端
		userDao.updateUserGpsMsg(openId, latitude, longitude);
		User user=userDao.selectUserInfoByOpenId(openId);
		//记录登录状态 用户登录状态由openid维护，但是必须要传入id
		if(user.getState()==1){
		session.setAttribute("openId", user.getOpenID());
		session.setAttribute("id", user.getId());
		}
		return user;
	}
		}
}
