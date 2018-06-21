package com.hotel.service.impl;

import com.hotel.dao.CommentDao;
import com.hotel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<com.hotel.models.Comment> getAllCommentByHotelId(Integer hotelId) {
        return commentDao.getAllCommentByHotelId(hotelId);
    }

    @Override
    public List<com.hotel.models.Comment> getCommentByHotelIdAndScore(Integer hotelId, Integer score) {
        return commentDao.getCommentByHotelIdAndScore(hotelId,score);
    }

    @Override
    public List<com.hotel.models.Comment> getCommectByHotelIdAndState(Integer hotelId, Integer state) {
        return commentDao.getCommentByHotelIdAndScore(hotelId,state);
    }

    @Override
    public List<com.hotel.models.Comment> getCommectByUid(String uid) {
        return commentDao.getCommectByUid(uid);
    }

    @Override
    public List<com.hotel.models.Comment> getCommectBySid(Integer sid) {
        return commentDao.getCommectBySid(sid);
    }
}
