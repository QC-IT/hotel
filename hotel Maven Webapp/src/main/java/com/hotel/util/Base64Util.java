package com.hotel.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;
/**
 * base64编码工具类
 * @author yuanhaohe
 *
 */
public final class Base64Util {
	/**
	 * 建议buff数组的最少长度为3 因为base64编码是每3个byte进行编码一次，不足的地方以=填充(请注意字符集，字符集出问题可能会导致某些问题)
	 * @since After jdk 1.8
	 * @param buff 需要进行编码的byte[] 图片 字符串等
	 * @return 编码后的base64字符串
	 */
public static String changeBytesToBase64String(byte[] buff){
	String base64String=Base64.getEncoder().encodeToString(buff);
	return base64String;
}

public static String changeStringToBase64String(String src,String charset){
	try {
		return Base64.getEncoder().encodeToString(src.getBytes(charset));
	} catch (UnsupportedEncodingException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	return null;
}



/**
 * base64字符串转byte[]
 * @param base64String base64字符串
 * @return 解密后的byte数组
 */
public static byte[] changeBase64StringToBytes(String base64String){
	byte[] buff=Base64.getDecoder().decode(base64String);
	return buff;
}
/**
 * base64字符串转指定字符集字符串
 * @param base64String base64字符串
 * @param charset 指定字符集
 * @return 解密后的字符串
 */
public static String changeBase64StringToString(String base64String,String charset){
return new String(Base64.getDecoder().decode(base64String),Charset.forName(charset));
}
}
