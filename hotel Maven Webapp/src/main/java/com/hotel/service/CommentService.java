package com.hotel.service;

import com.hotel.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentByHotelId(Integer hotelId);

    List<Comment> getCommentByHotelIdAndScore(Integer hotelId,Integer score);

    List<Comment> getCommectByHotelIdAndState(Integer hotelId,Integer state);

    List<Comment> getCommectByUid(String uid);

    List<Comment> getCommectBySid(Integer sid);
}
