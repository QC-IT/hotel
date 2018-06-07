package com.hotel.models;

public class HotelPic {
private int id;
private String hid;
private String picUrl;
private int state;
@Override
public String toString() {
	return "HotelPic [id=" + id + ", hid=" + hid + ", picUrl=" + picUrl + ", state=" + state + "]";
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
public String getPicUrl() {
	return picUrl;
}
public void setPicUrl(String picUrl) {
	this.picUrl = picUrl;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
}
