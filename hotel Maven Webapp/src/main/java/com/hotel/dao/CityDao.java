package com.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotel.models.City;
/**
 * 
 * @author yuanhaohe
 *
 */
public interface CityDao {
	/**
	 * 得到热门城市列表
	 * @return
	 */
public List<City> getHotCity();
/**
 * 得到全部城市列表
 * @return
 */
public List<City> getAllCity();
/**
 * 通过城市名称得到城市id
 * @param name
 * @return
 */
public Integer getCityIdByName(@Param("name")String name);
/**
 * 通过城市名称得到code
 * @param cityName
 * @return
 */
public String getCodeByCityName(@Param("cityName")String cityName);

/**
 * 添加城市
 * @param city
 */
public void addCity(City city);
/**
 * 根据城市code更改热门城市
 * @param code 城市code
 * @param hot 是否热门 1热门 0非热门
 */
public void updateCityHot(@Param("code")String code,@Param("hot")String hot);
/**
 * 修改城市信息
 * @param city
 */
public void updateCityById(City city);
/**
 * 删除一条城市信息
 * @param id
 */
public void deleteCityById(@Param("id")String id);
/**
 * 批量添加城市信息
 * @param citys
 */
public void addCityList(List<City> citys);

/**
 * 批量删除城市信息
 * @param ids 删除城市的id集合
 */
public void deleteCityList(List<String> ids);

}
