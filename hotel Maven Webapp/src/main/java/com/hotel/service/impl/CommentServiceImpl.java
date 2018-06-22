package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.dao.CommentDao;
import com.hotel.models.Comment;
import com.hotel.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

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
}
