package com.hotel.dao;

import com.hotel.models.Items;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ServiceDao {

	/**
	 * 根据酒店ID获取服务
	 * @return
	 */
	public List<Items> getItemsByHotelId(@Param("hid")String hid);

	/**
	 * 根据服务ID获取服务
	 * @return
	 */
	public Items getItemsById(@Param("id")Integer id);

	/**
	 * 根据服务项目查询服务
	 * @return
	 */
	public List<Items> getItemsByHotelIdAndItem(@Param("hid") String hid,@Param("item")String item);

	/**
	 * 根据状态查询服务
	 * @return
	 */
	public List<Items> getItemsByHotelIdAndState(@Param("hid") String hid,@Param("state")Integer state);

	/**
	 * 根据需要的等级查询服务
	 * @return
	 */
	public List<Items> getItemsByHotelIdAndNeedLevel(@Param("hid") String hid,@Param("needLevel")Integer needLevel);

	/**
	 * 添加服务
	 */
	public void addItems(Items items);

	/**
	 * 修改服务
	 * @param items
	 */
	public void updateItems(Items items);

	/**
	 * 根据id删除服务
	 * @param id
	 */
	public void deleteItems(@Param("id") Integer id);

	public List<Integer> selectSericeIdByUserIdAndNoComment(String uid);

	public List<Map<String, String>> selectSericeIdAllNoComment();
}
