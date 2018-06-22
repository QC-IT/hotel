package com.hotel.models;

import java.io.Serializable;
import java.util.Date;

public class Items implements Serializable{

	private static final long serialVersionUID = -7891397881526377600L;
private int id;
private String hid;
private String item;
private  int needLevel;
private String detailContent;
private String picUrl;
private Date beginTime;
private Date endTime;
private Date createTime;
private String state;
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getHid() {
	return hid;
}
public void setHid(String hid) {
	this.hid = hid;
}
public String getItem() {
	return item;
}
public void setItem(String item) {
	this.item = item;
}
public int getNeedLevel() {
	return needLevel;
}
public void setNeedLevel(int needLevel) {
	this.needLevel = needLevel;
}
public String getDetailContent() {
	return detailContent;
}
public void setDetailContent(String detailContent) {
	this.detailContent = detailContent;
}
public String getPicUrl() {
	return picUrl;
}
public void setPicUrl(String picUrl) {
	this.picUrl = picUrl;
}
public Date getBeginTime() {
	return beginTime;
}
public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
}
public Date getEndTime() {
	return endTime;
}
public void setEndTime(Date endTime) {
	this.endTime = endTime;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
@Override
public String toString() {
	return "Items [id=" + id + ", hid=" + hid + ", item=" + item + ", needLevel=" + needLevel + ", detailContent="
			+ detailContent + ", picUrl=" + picUrl + ", beginTime=" + beginTime + ", endTime=" + endTime
			+ ", createTime=" + createTime + ", state=" + state + "]";
}

}
