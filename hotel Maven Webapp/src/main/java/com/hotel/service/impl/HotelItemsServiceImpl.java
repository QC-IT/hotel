package com.hotel.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hotel.dao.HotelItemsDao;
import com.hotel.models.Items;
import com.hotel.redis.RedisService;
import com.hotel.service.HotelItemsService;
import com.hotel.util.PicUtil;

@Service
public class HotelItemsServiceImpl implements HotelItemsService {
	@Autowired
	private HotelItemsDao hotelItemsDao;
	@Autowired
	private RedisService redisService;
@Value("${hotel_service_pic}")
private String hotel_service_pic;
@Value("hotel_service_pic_filepath")
private String hotel_service_pic_filepath;
	
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

	@Override
	public boolean updateServicePic(MultipartFile file, String sid) {
try{
	if(file==null||sid==null||sid.trim().equals("")||!PicUtil.isPic(file.getOriginalFilename())){
		return false;
	}else{
		String orgName=file.getOriginalFilename();
		String newFile=hotel_service_pic_filepath+UUID.randomUUID()+orgName.substring(orgName.lastIndexOf("."));
		Items item=new Items();
		item.setId(Integer.valueOf(sid));
		item.setPicUrl(newFile);
		file.transferTo(new File(hotel_service_pic+orgName));
		return true;
	}
}catch(Exception e){
		return false;
		}
	}

	
}
