package com.hotel.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * MD5加密工具类
 * 
 * @author yuanhaohe
 *
 */
public final class MD5Util {

	/**
	 * 自定义加密次数和盐的通用可指定加密算法的加密方法
	 * 
	 * @param src
	 *            源字符串
	 * @param method
	 *            加密方法
	 * @param times
	 *            加密次数
	 * @param salt
	 *            盐
	 * @return 加密后字符串
	 */
	public static String md5Encrypt(String src, String method, int times, Object salt) {
		Object obj = new SimpleHash(method, src, salt, times);
		return obj.toString();
	}

	/**
	 * 自定义加密次数和盐的通用MD5加密方法
	 * 
	 * @param src
	 *            源字符串
	 * @param times
	 *            加密次数
	 * @param salt
	 *            盐
	 * @return 加密后字符串
	 */
	public static String md5Encrypt(String src, int times, Object salt) {
		Object obj = new SimpleHash("MD5", src, salt, times);
		return obj.toString();
	}

	/**
	 * 自定义加密次数的无盐的通用MD5加密方法
	 * 
	 * @param src
	 *            源字符串
	 * @param times
	 *            加密次数
	 * @return 加密后字符串
	 */
	public static String md5Encrypt(String src, int times) {

		return md5Encrypt(src, times, null);
	}

	/**
	 * 加密一次的无盐的通用MD5加密方法
	 * 
	 * @param src
	 *            源字符串
	 * @param times
	 *            加密次数
	 * @return 加密后字符串
	 */
	public static String md5Encrypt(String src) {
		return md5Encrypt(src, 1);
	}

}
