package com.hotel.models;

import java.io.Serializable;
import java.util.Date;

public class ServiceJoin implements Serializable{
 
	private static final long serialVersionUID = -3983858386039575369L;

	private Integer id;

    private String hid;

    private Integer sid;

    private String uid;

    private Date applytime;

    private Date audittime;

    private Integer state;

    private Integer iscomment;

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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIscomment() {
        return iscomment;
    }

    public void setIscomment(Integer iscomment) {
        this.iscomment = iscomment;
    }
}