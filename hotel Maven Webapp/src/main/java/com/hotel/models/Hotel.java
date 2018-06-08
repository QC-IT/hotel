package com.hotel.models;

import java.util.Date;
import java.util.List;

public class Hotel {
private String id;
private String name;
private String address;
private String longitude;
private String latitude;
private String administrator;
private String phoneNumber;
private Integer startLevel;
private String officialURL;
private String headPicURL;
private Integer type;
private Integer level;
private Date createTime;
private Integer state;
private String cityCode;
private Integer isRecommend;
private Integer recommendOrderCode;
private Double distance;
private List<HotelPic> picPath;
public List<HotelPic> getPicPath() {
	return picPath;
}
public void setPicPath(List<HotelPic> picPath) {
	this.picPath = picPath;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getAdministrator() {
	return administrator;
}
public void setAdministrator(String administrator) {
	this.administrator = administrator;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public Integer getStartLevel() {
	return startLevel;
}
public void setStartLevel(int startLevel) {
	this.startLevel = startLevel;
}
public String getOfficialURL() {
	return officialURL;
}
public void setOfficialURL(String officialURL) {
	this.officialURL = officialURL;
}
public String getHeadPicURL() {
	return headPicURL;
}
public void setHeadPicURL(String headPicURL) {
	this.headPicURL = headPicURL;
}
public Integer getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public Integer getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public Integer getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getCityCode() {
	return cityCode;
}
public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
}
public Integer getIsRecommend() {
	return isRecommend;
}
public void setIsRecommend(int isRecommend) {
	this.isRecommend = isRecommend;
}
public Integer getRecommendOrderCode() {
	return recommendOrderCode;
}
public void setRecommendOrderCode(int recommendOrderCode) {
	this.recommendOrderCode = recommendOrderCode;
}
public Double getDistance() {
	return distance;
}
public void setDistance(Double distance) {
	this.distance = distance;
}
@Override
public String toString() {
	return "Hotel [id=" + id + ", name=" + name + ", address=" + address + ", longitude=" + longitude + ", latitude="
			+ latitude + ", administrator=" + administrator + ", phoneNumber=" + phoneNumber + ", startLevel="
			+ startLevel + ", officialURL=" + officialURL + ", headPicURL=" + headPicURL + ", type=" + type + ", level="
			+ level + ", createTime=" + createTime + ", state=" + state + ", cityCode=" + cityCode + ", isRecommend="
			+ isRecommend + ", recommendOrderCode=" + recommendOrderCode + ", distance=" + distance + ", picPath="
			+ picPath + "]";
}
}
