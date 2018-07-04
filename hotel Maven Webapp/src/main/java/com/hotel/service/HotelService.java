package com.hotel.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.hotel.models.Hotel;

public interface HotelService {

	public static final String HOTEL_OPEN = "1";
	public static final String HOTEL_CLOSE = "0";
	public static final String HOTEL_REST = "2";

	/**
	 * 根据用户经纬度模糊查找酒店并根据距离排序
	 * 
	 * @param name
	 *            搜索输入的酒店名
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @return 搜索结果
	 * @throws Exception
	 */
	public List<Hotel> getSearchResult(String name, String longitude, String latitude) throws Exception;

	/**
	 * 根据城市code查找当前城市的推荐酒店
	 * 
	 * @param cityCode
	 *            城市code
	 * @return 推荐城市列表
	 * @throws Exception
	 */
	public List<Hotel> getRecomHotelListByCityCode(String cityCode, String page, String count) throws Exception;

	/**
	 * 通过城市名查找当前城市的推荐酒店
	 * 
	 * @param name
	 *            城市名称(不能以市，区等结尾)
	 * @param page
	 *            页数
	 * @param count
	 *            数量
	 * 
	 * @return 推荐城市列表
	 * @throws Exception
	 */
	public List<Hotel> getRecomHotelListByCityName(String name, String page, String count) throws Exception;

	/**
	 * 将酒店状态转换为关闭
	 * 
	 * @param id
	 *            酒店ID
	 * @return 结果true|false
	 * @throws Exception
	 */
	public boolean closeHotel(String id) throws Exception;

	/**
	 * 将酒店状态转换为打开
	 * 
	 * @param id
	 *            酒店ID
	 * @return 结果true|false
	 * @throws Exception
	 */
	public boolean openHotel(String id) throws Exception;

	/**
	 * 将酒店状态转换为未营业
	 * 
	 * @param id
	 *            酒店ID
	 * @return 结果true|false
	 * @throws Exception
	 */
	public boolean restHotel(String id) throws Exception;

	/**
	 * 更新酒店基本信息
	 * 
	 * @param hotel
	 *            酒店基本信息
	 * @return 是否添加成功
	 * @throws Exception
	 */
	public boolean updateHotelBaseInfo(Hotel hotel) throws Exception;

	/**
	 * 增加酒店基本信息
	 * 
	 * @param hotel
	 * @return 是否增加成功
	 * @throws Exception
	 */
	public boolean insertHotelBaseInfo(Hotel hotel) throws Exception;

	/**
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @param index
	 *            位置
	 * @param amount
	 *            数量
	 * @param session
	 * @return 查找到的商家数量
	 * @throws Exception
	 */
	public List<Hotel> getNearHotelLimit(String lat, String lng, String index, String amount, HttpSession session)
			throws Exception;

	/**
	 * 通过id查找Hotel
	 * 
	 * @param id
	 * @return
	 */
	public Hotel getHotelById(String id);
	
	/**
	 * 修改上传酒店主页图片
	 * @param file
	 * @param hid
	 * @return
	 */
	public boolean updateOfficialPic(MultipartFile file,String hid);
	
	/**
	 * 修改酒店头像图片
	 * @param file
	 * @param hid
	 * @return
	 */
	public boolean updateHeadPic(MultipartFile file,String hid);
}
