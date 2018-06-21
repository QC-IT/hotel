package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.HotelItemsDao;
import com.hotel.models.Items;
import com.hotel.service.HotelItemsService;


@Service
public class HotelItemsServiceImpl implements HotelItemsService {
	@Autowired
	private HotelItemsDao hotelItemsDao;

	@Override
	public List<Items> getHotelItems(String hotelId) throws Exception {
		if (hotelId != null && !hotelId.trim().equals("")) {
			return hotelItemsDao.getHotelItemsListByHotelId(hotelId);
		} else {
			return null;
		}
	}

	@Override
	public void insertItems(Items items) throws Exception {
		if(items!=null){
		hotelItemsDao.insertItems(items);
		}
	}

	@Override
	public void updataItems(Items items) throws Exception {
		if (items != null) {
			if (items.getId() == 0) {
				throw new Exception();
			} else {
				hotelItemsDao.updateItems(items);
			}
		}
	}

	@Override
	public void cancelItemsByItemsId(String id) throws Exception {
		if (id != null && !id.trim().equals("")) {
			hotelItemsDao.updateItemsState(id, "0");
		}

	}

	@Override
	public void openItemsByItemsId(String id) throws Exception {
		if (id != null && !id.trim().equals("")) {
			hotelItemsDao.updateItemsState(id, "1");
		}

	}

}
