package com.hotel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.CommentExtendsDao;
import com.hotel.dao.ServiceDao;
import com.hotel.models.Comment;
import com.hotel.service.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService{
	@Autowired
private ServiceDao serviceDao;
	@Autowired
private CommentExtendsDao commentDao;
	@Override
	public boolean autoComment() {
		try{
	List<Map<String,String>> list=serviceDao.selectSericeIdAllNoComment();
	for(Map<String,String> map:list){
		Comment comment=new Comment();
		comment.setContent("此用户没有评价，系统默认好评");
		comment.setScore(5);
		comment.setIsanonymous(0);
		comment.setSid(Integer.parseInt(map.get("sid")));
		comment.setTime(new Date());
		comment.setState(1);
		comment.setUid(map.get("uid"));
		commentDao.addComment(comment);
	}
	return true;
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
	}

}
