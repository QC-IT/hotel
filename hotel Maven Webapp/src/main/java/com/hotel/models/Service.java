package com.hotel.models;

import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable {
	private static final long serialVersionUID = 6843866045759500159L;

	private Integer id;

	private String hid;

	private String item;

	private Integer needlevel;

	private String picurl;

	private Date begintime;

	private Date endtime;

	private Date createtime;

	private Boolean state;

	private String detailcontent;
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid == null ? null : hid.trim();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item == null ? null : item.trim();
	}

	public Integer getNeedlevel() {
		return needlevel;
	}

	public void setNeedlevel(Integer needlevel) {
		this.needlevel = needlevel;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl == null ? null : picurl.trim();
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getDetailcontent() {
		return detailcontent;
	}

	public void setDetailcontent(String detailcontent) {
		this.detailcontent = detailcontent == null ? null : detailcontent.trim();
	}
}