package com.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotel.models.Items;
/**
 * @author yuanhaohe
 *
 */
public interface HotelItemsDao {
	
/**
 * 通过酒店ID得到服务列表
 * @param hid
 * @return 服务信息
 */
	public List<Items> getHotelItemsListByHotelId(String hid);
	
	/**
	 * 添加酒店服务
	 * @param items
	 */
	public void insertItems(Items items);
	
	/**
	 * 根据服务id改变酒店服务状态
	 * @param id
	 * @param state
	 */
	public void updateItemsState(@Param("id")String id,@Param("state")String state);
	
	/**
	 * 更新服务信息
	 * @param items
	 */
	public void updateItems(Items items);
	/**
	 * 通过id得到items
	 * @param id
	 * @return
	 */
	public Items getItemsByItemsId(String id);
	
	/**
	 * 通过酒店ID以及种类得到服务列表
	 * @param hid
	 * @param type
	 * @return
	 */
	public List<Items> getHotelItemsListByHotelIdAndType(@Param("hid")String hid,@Param("type")String type);
}
