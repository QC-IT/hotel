package com.hotel.service;

import com.github.pagehelper.PageInfo;
import com.hotel.models.Comment;

public interface CommentService {
	 public PageInfo<Comment> getAllCommentByHotelId(String hotelId,int page,int rows);

	 public PageInfo<Comment> getCommentByHotelIdAndScore(String hotelId, Integer score,int page,int rows);

	 public PageInfo<Comment> getCommectByHotelIdAndState(String hotelId, Integer state,int page,int rows);

	 public PageInfo<Comment> getCommectByUid(String uid,int page,int rows);

	 public PageInfo<Comment> getCommectBySid(Integer sid,int page,int rows) ;
	 
		public boolean autoComment(String uid);
}
