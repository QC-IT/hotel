package com.hotel.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hotel.models.User;
import com.hotel.service.UserService;
import com.hotel.util.PicUtil;

@Controller
@RequestMapping(value = "/user")
public class UserInfoController {
@Autowired
private UserService userService;
private static final Logger logger=LoggerFactory.getLogger(UserInfoController.class);

	@RequestMapping(value = "/updateUser.json", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String updateUser(@RequestBody User user) {
		boolean flag=userService.updateUserInfo(user);
		if(flag){
			logger.debug("{\"code\":\"200\",\"msg\":\"success\"}");
			return "{\"code\":\"200\",\"msg\":\"success\"}";
		}else{
			logger.debug("{\"code\":\"500\",\"msg\":\"failed\"}");
			return "{\"code\":\"500\",\"msg\":\"failed\"}";
		}
	}
	
	@RequestMapping(value="uploadUserHeadPic.json",method=RequestMethod.POST,produces="application/json;charset=utf-8")
public @ResponseBody String uploadUserHeadPic(MultipartFile file,HttpSession session){
		String openId=(String) session.getAttribute("openId");
		if(openId==null){
			logger.debug("{\"code\":400,\"msg\":\"用户未登录\"}");
			return  "{\"code\":400,\"msg\":\"用户未登录\"}";
		}
		try{
		if(file!=null){
			String orgFileName=file.getOriginalFilename();
			System.out.println(orgFileName);
			if(PicUtil.isPic(orgFileName)){
				file.transferTo(new File(userService.getHeadFilePath()+userService.getUserHeadPicPath(orgFileName)));
				System.out.println(userService.getHeadFilePath());
				System.out.println(userService.getHeadFilePath()+userService.getUserHeadPicPath(orgFileName));
				User user =new User();
				user.setOpenID(openId);
				user.setHeadPic(userService.getUserHeadPicPath(orgFileName));
				userService.updateUserInfo(user);
				logger.debug( "{\"code\":200,\"msg\":\"头像上传成功\"}");
				return "{\"code\":200,\"msg\":\"头像上传成功\"}";
			}else{
				logger.debug("{\"code\":500,\"msg\":\"格式不支持\"}");
				return "{\"code\":500,\"msg\":\"格式不支持\"}";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("{\"code\":500,\"msg\":\"未知错误\"}"+e.getMessage());
			return "{\"code\":500,\"msg\":\"未知错误\"}";
		}
		logger.debug("{\"code\":500,\"msg\":\"文件为空\"}");
	return "{\"code\":500,\"msg\":\"文件为空\"}";
	
		
}
	
}
