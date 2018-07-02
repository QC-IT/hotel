package com.hotel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotel.models.User;
import com.hotel.service.LoginService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private LoginService loginService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 传入code获取openId
	 * 
	 * @param session
	 * @param map
	 *            code
	 * @return
	 */
	@RequestMapping(value = "getOpenId.json", method=RequestMethod.POST,produces = "appication/json;charset=utf8")
	public @ResponseBody String getOpenId(HttpSession session, @RequestBody Map<String, String> map) {
		String code = map.get("code");
		String openId = loginService.getWeChatOpenIdByCode(code);
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("openId", openId);
		data.put("SESSION", session.getId());
		JSONObject json=new JSONObject();
		json.put("code", 200);
		json.put("data", data);
		logger.debug(json.toJSONString());
		return json.toJSONString();
	}

	/**
	 * 用户登录接口
	 * 
	 * @param request
	 * @param map
	 *            code latitude longitude
	 * @return
	 */
	@RequestMapping(value = "login.json", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public @ResponseBody String login(HttpServletRequest request, @RequestBody Map<String, String> map) {
		String code = map.get("code");
		String latitude = map.get("latitude");
		String longitude = map.get("longitude");
		String nickName = map.get("nickName");
		HttpSession session = request.getSession();
		String openId = loginService.getWeChatOpenIdByCode(code);
		logger.debug("openId:" + openId + " 用户登录 longitude:" + longitude + " latitude:" + latitude);
		User user = loginService.loginUser(openId, latitude, longitude,nickName,session);
		Map<String,Object> data=new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		if (user.getState() == 1) {
			json.put("code", "200");
			data.put("user", user);
		} else if (user.getState() == 3) {
			json.put("code", 202);
			data.put("msg", "用户违规禁止登录");
		}
		json.put("data", data);
		logger.debug(json.toJSONString());
		return json.toJSONString();
	}

}
