package com.hotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.dao.CommentExtendsDao;
import com.hotel.dao.ServiceDao;
import com.hotel.models.Comment;
import com.hotel.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
private ServiceDao serviceDao;
	@Autowired
private CommentExtendsDao commentDao;

    @Override
    public PageInfo<Comment> getAllCommentByHotelId(String hotelId,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getAllCommentByHotelId(hotelId);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommentByHotelIdAndScore(String hotelId, Integer score,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommentByHotelIdAndScore(hotelId, score);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommectByHotelIdAndState(String hotelId, Integer state,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommentByHotelIdAndScore(hotelId,state);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommectByUid(String uid,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommectByUid(uid);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommectBySid(Integer sid,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommectBySid(sid);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

	@Override
	public boolean autoComment(String uid) {
		if(uid!=null&&!uid.trim().equals("")){
			List<Integer> list= serviceDao.selectSericeIdByUserIdAndNoComment(uid);
			for( Integer in:list){
				Comment comment=new Comment();
				comment.setContent("此用户没有评价，系统默认好评");
				comment.setScore(5);
				comment.setIsanonymous(0);
				comment.setSid(in);
				comment.setTime(new Date());
				comment.setState(1);
				comment.setUid(uid);
				commentDao.addComment(comment);
			}
			return true;
		}else{
			return false;
		}
	}
}
