package com.hotel.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.UserJoinDao;
import com.hotel.models.ServiceJoin;
import com.hotel.service.UserJoinService;
@Service
public class UserJoinServiceImpl implements UserJoinService{
@Autowired
private UserJoinDao userJoinDao;
@Transactional
	public String userJoinService(Integer sid, String openid, String hid) {
		try{
			ServiceJoin sj=new ServiceJoin();
			sj.setHid(hid);
		sj.setSid(sid);
		sj.setUid(openid);

			userJoinDao.addNewUserJoinMsgReturnId(sj);
			return String.valueOf(sj.getId());
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
@Transactional
	@Override
	public boolean approvedService(String id) {
	try{
		ServiceJoin sj=new ServiceJoin();
		sj.setId(Integer.valueOf(id));
		sj.setAudittime(new Date());
		sj.setState(2);
		System.out.println(sj);
		userJoinDao.updateUserJoinService(sj);
		return true;
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
	
	}
@Transactional
	@Override
	public boolean unapprovedService(String id) {
		try{
			ServiceJoin sj=new ServiceJoin();
			sj.setId(Integer.valueOf(id));
			sj.setAudittime(new Date());
			sj.setState(3);
			userJoinDao.updateUserJoinService(sj);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
