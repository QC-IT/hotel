package com.hotel.models;

import java.io.Serializable;

public class City implements Serializable{
	private static final long serialVersionUID = 7177693423376560284L;
	private String id;
	private String name;
	private String abbr;
	private String code;
	private String pinyin;
	private String ishot;
	public String getIshot() {
		return ishot;
	}
	public void setIshot(String ishot) {
		this.ishot = ishot;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", abbr=" + abbr + ", code=" + code + ", pinyin=" + pinyin
				+ ", ishot=" + ishot + "]";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}