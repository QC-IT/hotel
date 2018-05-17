package com.hotel.exceptions;
/**
 * @author yuanhaohe
 *百度地图经纬度格式异常
 */
public class BaiduMapLocationFormatException extends Exception{

	private static final long serialVersionUID = 7325490569376744317L;

	public BaiduMapLocationFormatException(String msg){
		super(msg);
	}
	
	
}
