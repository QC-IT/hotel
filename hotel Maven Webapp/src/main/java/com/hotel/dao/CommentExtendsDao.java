package com.hotel.dao;

import com.hotel.models.Comment;

public interface CommentExtendsDao extends CommentDao{
	
	public void addComment(Comment comment);

}
