package com.hotel.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {

	private static final long serialVersionUID = -3966041605531689762L;

	private Integer id;

	private String uid;

	private Integer sid;

	private Date time;

	private Integer score;

	private Integer state;

	private Integer isanonymous;

	private String content;
	private List<CommentPic> commentPics;
public List<CommentPic> getCommentPics(){
	return this.commentPics;
}
	
	public void setCommentPics(List<CommentPic> commentPics) {
		this.commentPics = commentPics;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid == null ? null : uid.trim();
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsanonymous() {
		return isanonymous;
	}

	public void setIsanonymous(Integer isanonymous) {
		this.isanonymous = isanonymous;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}