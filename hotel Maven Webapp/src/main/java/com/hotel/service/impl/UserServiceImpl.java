package com.hotel.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.UserDao;
import com.hotel.models.User;
import com.hotel.service.UserService;
@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserDao userDao;
private static final Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
@Value("${user_head_pic}")
private  String userHeadPic;
@Value("${user_head_pic_filepath}")
private String userHeadPicPath;
	@Override
	@Transactional
	public boolean updateUserInfo(User user) {
		if(user!=null){
		try{
	userDao.updateUser(user);
	logger.debug("修改用户:"+user);
	return true;
	}catch(Exception e){
		e.printStackTrace();
		logger.debug("用户修改失败!"+user);
		return false;
	}
		}else return false;
		
	}
	@Override
	public String getUserHeadPicPath(String orgFileName) {
		String fileName=userHeadPicPath+UUID.randomUUID()+orgFileName.substring(orgFileName.lastIndexOf("."));
		return fileName;
	}
	@Override
	public String getHeadFilePath() {
		return userHeadPic;
	}
	@Override
	public List<String> getAllUserOpenId() {
	
		return userDao.getUserOpenId();
	}


}
