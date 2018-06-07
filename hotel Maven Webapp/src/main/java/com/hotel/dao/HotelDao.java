package com.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotel.models.Hotel;

public interface HotelDao {

	public List<Hotel> getHotelInfoByCityCodeAndName(@Param("cityCode")String cityCode,@Param("name")String name);
	
	public List<Hotel> getRecommendHotelByCity(String cityCode);
}
