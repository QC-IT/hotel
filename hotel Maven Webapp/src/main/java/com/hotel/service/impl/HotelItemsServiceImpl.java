package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.dao.HotelItemsDao;
import com.hotel.models.Items;
import com.hotel.redis.RedisService;
import com.hotel.service.HotelItemsService;

@Service
public class HotelItemsServiceImpl implements HotelItemsService {
	@Autowired
	private HotelItemsDao hotelItemsDao;
	@Autowired
	private RedisService redisService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Items> getHotelItems(String hotelId) throws Exception {
		// 城市服务相关 hotel-items-所属项-所属项id
		if (hotelId != null && !hotelId.trim().equals("")) {
			if (redisService.exists("hotel-items-hotel-" + hotelId)) {
				return (List<Items>) redisService.get("hotel-items-hotel-" + hotelId);
			} else {
				List<Items> list = hotelItemsDao.getHotelItemsListByHotelId(hotelId);
				redisService.set("hotel-items-hotel-" + hotelId, list,24*60*60l);
				return list;
			}

		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void insertItems(Items items) throws Exception {
		if (items != null) {
			hotelItemsDao.insertItems(items);
			if(redisService.exists("hotel-items-hotel-" + items.getHid())){
				redisService.remove("hotel-items-hotel-" + items.getHid());
			}
		}
	}

	@Override
	@Transactional
	public void updataItems(Items items) throws Exception {
		if (items != null) {
			if (items.getId() == 0) {
				throw new Exception();
			} else {
				hotelItemsDao.updateItems(items);
				if(redisService.exists("hotel-items-hotel-" + items.getHid())){
					redisService.remove("hotel-items-hotel-" + items.getHid());
				}
			}
		}
	}

	@Override
	@Transactional
	public void cancelItemsByItemsId(String id) throws Exception {
		if (id != null && !id.trim().equals("")) {
			hotelItemsDao.updateItemsState(id, "0");
			Items items=hotelItemsDao.getItemsByItemsId(id);
			if(redisService.exists("hotel-items-hotel-" + items.getHid())){
				redisService.remove("hotel-items-hotel-" + items.getHid());
			}
		}

	}

	@Override
	@Transactional
	public void openItemsByItemsId(String id) throws Exception {
		if (id != null && !id.trim().equals("")) {
			hotelItemsDao.updateItemsState(id, "1");
			Items items=hotelItemsDao.getItemsByItemsId(id);
			if(redisService.exists("hotel-items-hotel-" + items.getHid())){
				redisService.remove("hotel-items-hotel-" + items.getHid());
			}
		}

	}

}
