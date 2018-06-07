package com.hotel.service;

import javax.servlet.http.HttpSession;

import com.hotel.models.User;
/**
 * 用户登录相关接口
 * @author yuanhaohe
 *
 */
public interface LoginService {
	/**
	 * 通过code获取openId
	 * @param code 微信传给服务器的code
	 * @return 得到的openId
	 */
public String getWeChatOpenIdByCode(String code);
/**
 * 用户登录接口
 * @param openId 微信的openId
 * @param latitude 微信传的纬度
 * @param longitude 微信传的经度
 * @param session session
 * @return 登录后的user对象
 */
public User loginUser(String openId,String latitude,String longitude,HttpSession session);
}
