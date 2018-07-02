package com.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotel.models.Hotel;

/**
 * 酒店操作DAO
 * 
 * @author yuanhaohe
 *
 */
public interface HotelDao {
	/**
	 * 通过酒店名字模糊查找营业酒店信息
	 * 
	 * @param cityCode
	 * @param name
	 * @return 查找到的酒店
	 */
	public List<Hotel> getHotelInfoByCityCodeAndName(@Param("name") String name);

	/**
	 * 根据酒店所在城市的code查找推荐酒店
	 * 
	 * @param cityCode
	 *            城市code
	 * @param page
	 *            页码
	 * 
	 * @param count
	 *            数量
	 * @return 推荐城市信息
	 */
	public List<Hotel> getRecommendHotelByCity(@Param("cityCode")String cityCode, @Param("index")int index, @Param("count")int count);

	/**
	 * 根据酒店ID和状态码将酒店状态改变
	 * 
	 * @param id
	 *            酒店ID
	 * @return 返回影响条数
	 */
	public void changeHotelStateById(@Param("id") String id, @Param("state") String state);

	/**
	 * 更新酒店基本信息（建议是在数据库中查找到当前对象再更新）
	 * 
	 * @param hotel
	 *            更新hotel对象里的内容建议是在数据库中查找到当前对象再更新
	 */
	public void updateHotelBaseInfo(Hotel hotel);

	/**
	 * 添加酒店信息
	 * 
	 * @param hotel
	 */
	public void insertHotelBaseInfo(Hotel hotel);

	/**
	 * 根据城市code查找酒店
	 * 
	 * @param cityCode
	 * @return 酒店列表
	 */
	public List<Hotel> selectHotelByCityCode(String cityCode);

	/**
	 * 通过酒店的id获取hotel
	 * 
	 * @param id
	 *            酒店id
	 * @return
	 */
	public Hotel getHotelById(String id);
}
