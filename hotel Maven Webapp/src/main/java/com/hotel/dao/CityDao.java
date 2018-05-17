package com.hotel.dao;

import java.util.List;

import com.hotel.models.City;

public interface CityDao {
public List<City> getHotCity();

public List<City> getAllCity();

public int getCityIdByName(String name);
}
