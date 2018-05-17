package com.hotel.models;

public class City{
	private String id;
	private String name;
	private String abbr;
	private String code;
	private String pinyin;
	
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", abbr=" + abbr + ", code=" + code + ", pinyin=" + pinyin + "]";
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