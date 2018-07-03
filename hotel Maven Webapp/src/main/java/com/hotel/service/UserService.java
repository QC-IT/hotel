package com.hotel.service;

import com.hotel.models.User;

public interface UserService {
	
	/**
	 * 更新用户基本信息
	 * @param user
	 * @return 成功true 失败false
	 */
	public boolean updateUserInfo(User user);

	/**
	 * 得到图片相对路径
	 * @param orgFileName
	 * @return 相对路径
	 */
	public String getUserHeadPicPath(String orgFileName);

	/**
	 * 得到图片绝对路径
	 * @return 绝对路径
	 */
	public String getHeadFilePath();

}
