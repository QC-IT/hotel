package com.hotel.models;

import java.util.Date;

public class Comment {
    //评论id
    private Integer id;
    //用户ID
    private String uid;
    //服务id
    private Integer sid;

    private Date time;
    //评分 12345星
    private Integer score;
    //评论状态 1正常 2关闭 3删除 4违规
    private Integer state;
    //是否匿名
    private Integer isanonymous;
    //服务内容
    private String content;

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