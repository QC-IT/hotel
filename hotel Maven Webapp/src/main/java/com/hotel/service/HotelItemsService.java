package com.hotel.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hotel.models.Items;
/**
 * 酒店方服务处理service接口
 * @author yuanhaohe
 *
 */
public interface HotelItemsService {
	/**
	 * 根据酒店ID查找酒店服务列表
	 * @param hotelId
	 * @return
	 */
public List<Items> getHotelItems(String hotelId) throws Exception;
/**
 * 增加酒店服务
 * @param items
 */
public void insertItems(Items items) throws Exception;
/**
 * 酒店方关闭该服务
 * @param id 服务id
 */
public void cancelItemsByItemsId(String id)throws Exception;

/**
 * 酒店方重新打开该服务
 * @param id 服务id
 */
public void openItemsByItemsId(String id)throws Exception;
/**
 * 酒店方更新服务信息
 * @param items 服务信息 id必传 创建时间等一些特殊字段无法更改
 */
public void updataItems(Items items)throws Exception;

/**
 * 更新酒店服务图片
 * @param file
 * @param sid
 * @return
 */
public boolean updateServicePic(MultipartFile file,String sid);

/**
 * 通过酒店ID和种类得到服务列表
 * @param hid
 * @param type
 * @return
 */
public List<Items> getItemsByHidAndType(String hid,Integer type);
}
