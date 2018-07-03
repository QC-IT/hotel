package com.hotel.util;

public final class PicUtil {
public static boolean isPic(String name){
	if(name.endsWith("jpg")){
		return true;
	}else if(name.endsWith("png")){
		return true;
	}else if(name.endsWith("bmp")){
		return true;
	}else if(name.endsWith("jpg")){
	return true;	
	}else return false;
}
}
