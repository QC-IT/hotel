package com.hotel.dao;

import org.apache.ibatis.annotations.Param;

import com.hotel.models.User;

public interface UserDao {
	/**
	 *  插入用户信息
	 * @param openId openid
	 * @param id 用户id
	 * @param nickName 昵称
	 */
	public void insertUser(@Param("openId") String openId, @Param("id") String id, @Param("nickName") String nickName);

	public int selectCountUserByOpenId(String openId);

	public User selectUserInfoByOpenId(String openId);

	public void updateUserGpsMsg(@Param("openId") String openId, @Param("latitude") String latitude,
			@Param("longitude") String longitude);

	public void updateUserStateByOpenId(@Param("openId") String openId, @Param("state") String state);
}
