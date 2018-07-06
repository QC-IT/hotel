package com.hotel.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.models.Items;
import com.hotel.service.ServiceService;
import com.hotel.service.UserJoinService;

@Controller
@RequestMapping(value = "/service")
public class UserJoinServiceController {
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private UserJoinService userJoinService;
	private final static Logger logger=LoggerFactory.getLogger(UserJoinServiceController.class);
/**
 * 用户申请加入服务项目
 * @param param
 * @param session
 * @return
 */
	@RequestMapping(value = "/joinService.json", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String joinOnService(@RequestBody Map<String, String> param, HttpSession session) {
		String serviceId = param.get("sid");
		String openId = (String) session.getAttribute("openId");
		Items items = serviceService.getItemsById(Integer.valueOf(serviceId));
		if (items != null) {
			String id = userJoinService.userJoinService(items.getId(), openId, items.getHid());
			if (id != null) {
				logger.debug("{\"code\":200,\"msg\":\"申请成功!\",\"id\":" + id + "}");
				return "{\"code\":200,\"msg\":\"申请成功!\",\"id\":" + id + "}";
			} else {
				logger.debug("{\"code\":500,\"msg\":\"申请失败成功!\"}");
				return "{\"code\":500,\"msg\":\"申请失败成功!\"}";
			}
		} else {
			logger.debug( "{\"code\":500,\"msg\":\"未找到对应服务!\"}");
			return "{\"code\":500,\"msg\":\"未找到对应服务!\"}";
		}
	}
	
/**
 * 酒店方面通过申请
 * @param param
 * @return
 */
	@RequestMapping(value = "/approvedService.json", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String approvedService(@RequestBody Map<String, String> param) {
		String id = param.get("id");
		boolean flag = userJoinService.approvedService(id);
		if (flag) {
			logger.debug("{\"code\":200,\"msg\":\"操作成功!\"}");
			return "{\"code\":200,\"msg\":\"操作成功!\"}";
		}
		logger.debug("{\"code\":500,\"msg\":\"系统出现异常!\"}");
		return "{\"code\":500,\"msg\":\"系统出现异常!\"}";
	}
	
/**
 * 酒店方面拒绝通过申请
 * @param param
 * @return
 */
	@RequestMapping(value = "/unapprovedService.json", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String unapprovedService(@RequestBody Map<String, String> param) {
		String id = param.get("id");
		boolean flag = userJoinService.unapprovedService(id);
		if (flag) {
			logger.debug("{\"code\":200,\"msg\":\"操作成功!\"}");
			return "{\"code\":200,\"msg\":\"操作成功!\"}";
		}
		logger.debug("{\"code\":500,\"msg\":\"系统出现异常!\"}");
		return "{\"code\":500,\"msg\":\"系统出现异常!\"}";
	}

}
