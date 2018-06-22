package com.hotel.models;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	private static final long serialVersionUID = -4208763195206013293L;
private String id;
private String openID;
private String userName;
private String password;
private String nickName;
private String headPic;
private String gender;
private Integer age;
private String phoneNumber;
private String email;
private String salt;
private Date createTime;
private String latitude;
private String longitude;
private Integer state;
private Integer level;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getOpenID() {
	return openID;
}
public void setOpenID(String openID) {
	this.openID = openID;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNickName() {
	return nickName;
}
public void setNickName(String nickName) {
	this.nickName = nickName;
}
public String getHeadPic() {
	return headPic;
}
public void setHeadPic(String headPic) {
	this.headPic = headPic;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSalt() {
	return salt;
}
public void setSalt(String salt) {
	this.salt = salt;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}
public Integer getLevel() {
	return level;
}
public void setLevel(Integer level) {
	this.level = level;
}
@Override
public String toString() {
	return "User [id=" + id + ", openID=" + openID + ", userName=" + userName + ", password=" + password + ", nickName="
			+ nickName + ", headPic=" + headPic + ", gender=" + gender + ", age=" + age + ", phoneNumber=" + phoneNumber
			+ ", email=" + email + ", salt=" + salt + ", createTime=" + createTime + ", latitude=" + latitude
			+ ", longitude=" + longitude + ", state=" + state + ", level=" + level + "]";
}


}
