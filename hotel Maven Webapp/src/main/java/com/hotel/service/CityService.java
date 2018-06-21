package com.hotel.service;

import java.util.List;

import com.hotel.models.City;

public interface CityService {
	
	/**
	 * 得到热门城市列表
	 * @return 热门城市列表
	 * @throws Exception
	 */
	public List<City> getHotCityList() throws Exception;
	/**
	 * 得到全部城市列表
	 * @return 全部城市列表
	 * @throws Exception
	 */
	public List<City> getAllCityList()throws Exception;
	
	/**
	 * 通过城市名查找城市id
	 * @param name 城市名
	 * @return 城市id 查不到返回0
	 * @throws Exception
	 */
	public int selectCityIdByName(String name) throws Exception;
	
	/**
	 * 通过城市名得到城市code
	 * @param cityName 城市名
	 * @return 城市code
	 * @throws Exception
	 */
	public String getCodeByCityName(String cityName) throws Exception;
	/**
	 * 添加城市列表信息
	 * @param citys 批量城市信息
	 */
	public void insertCityList(List<City> citys) throws Exception;
	/**
	 * 批量删除城市
	 * @param ids 城市id集合
	 * @throws Exception
	 */
	public void deleteCityList(List<String> ids) throws Exception;
	/**
	 * 插入一条城市信息
	 * @param city
	 * @throws Exception
	 */
	public void  insertCity(City city)throws Exception;
	
	public void deleteCity(String id)throws Exception;
}
