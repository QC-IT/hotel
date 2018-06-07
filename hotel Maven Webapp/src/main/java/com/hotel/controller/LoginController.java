package com.hotel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotel.models.User;
import com.hotel.service.LoginService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	private static final Logger logger=LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value="getOpenId.json",method=RequestMethod.POST,produces="appication/json;charset=utf8")
	public @ResponseBody String getOpenId(HttpSession session, @RequestParam("code") String code) {
		String openId = loginService.getWeChatOpenIdByCode(code);
		logger.debug("获取openId:"+openId);
		return "{\"code\":200,\"openId\":\"" + openId + "\"}";
	}

	@RequestMapping(value="login.json",method=RequestMethod.POST,produces="application/json;charset=utf8")
	public @ResponseBody String login(HttpServletRequest request, @RequestParam("code") String code,
			@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) {
		HttpSession session = request.getSession();
		String openId = loginService.getWeChatOpenIdByCode(code);
		logger.debug("openId:"+openId+" 用户登录 longitude:"+longitude+" latitude:"+latitude);
		User user = loginService.loginUser(openId, latitude, longitude, session);
		JSONObject json = new JSONObject();
		json.put("code", "200");
		json.put("user", user);
		return json.toJSONString();
	}

}
