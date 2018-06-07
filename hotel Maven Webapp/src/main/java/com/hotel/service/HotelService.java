package com.hotel.service;

import java.util.List;

import com.hotel.models.Hotel;

public interface HotelService {
	public List<Hotel> getSearchResult(String name,String longitude,String latitude);
	
	public List<Hotel> getRecomHotelListByCityCode(String cityCode);
	
	public List<Hotel> getRecomHotelListByCityName(String name);

}
