package com.hotel.dao;

import org.apache.ibatis.annotations.Param;

import com.hotel.models.ServiceJoin;

public interface UserJoinDao {
	/**
	 * 将用户增加到用户服务关联表中，不返回主键
	 * 
	 * @param hid
	 * @param sid
	 * @param uid
	 */
	public void addNewUserJoinMsg(@Param("hid") String hid, @Param("sid") String sid, @Param("uid") String uid);

	/**
	 * * 将用户增加到用户服务关联表中，返回主键
	 * 
	 * @param sj
	 */
	public void addNewUserJoinMsgReturnId(ServiceJoin sj);

	/**
	 * 更新用户服务关联表
	 * 
	 * @param sj
	 */
	public void updateUserJoinService(ServiceJoin sj);

}
