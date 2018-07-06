package com.hotel.service;

public interface UserJoinService {
	/**
	 * 用户申请服务
	 * 
	 * @param sid
	 *            服务ID
	 * @param openid
	 *            用户OpenID
	 * @param hid
	 *            酒店ID
	 * @return
	 */
	public String userJoinService(Integer sid, String openid, String hid);

	/**
	 * 酒店方通过用户申请
	 * 
	 * @param id
	 *            用户服务关联表ID
	 * @return
	 */
	public boolean approvedService(String id);

	/**
	 * 酒店方拒绝通过用户申请
	 * 
	 * @param id
	 *            用户服务关联表ID
	 * @return
	 */
	public boolean unapprovedService(String id);
}
