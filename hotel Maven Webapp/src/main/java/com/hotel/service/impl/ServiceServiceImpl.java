package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.hotel.dao.ServiceDao;
import com.hotel.models.Items;
import com.hotel.redis.RedisService;
import com.hotel.service.ServiceService;
@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private RedisService redisService;

    //redis 查询前缀
    @Value("${REDIS_ITEMS_PRE}")
    private String REDIS_ITEMS_PRE;

    //redis 超时时间3天
    @Value("${REDIS_ITEMS_ITME_OUT}")
    private Long REDIS_ITEMS_ITME_OUT;


    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public PageInfo<Items> getItemsByHotelId(String hid,int page, int rows) {
        String key = REDIS_ITEMS_PRE+":"+hid+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        //start之后 所有的查询都为分页
        PageHelper.startPage(page,rows);
        List<Items> list = serviceDao.getItemsByHotelId(hid);
        //封装成携带分页信息详细信息的数据
        PageInfo pageInfo = new PageInfo(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo ;
    }

    @Override
    public Items getItemsById(Integer id) {
        String key = REDIS_ITEMS_PRE+":"+id;
        String json = (String)redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            Items items = JSON.parseObject(json, Items.class);
            return items;
        }
        Items items = serviceDao.getItemsById(id);
        redisService.set(key,JSON.toJSONString(items),REDIS_ITEMS_ITME_OUT);
        return items;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PageInfo<Items> getItemsByHotelIdAndItem(String hid,String item, int page, int rows) {
        String key = REDIS_ITEMS_PRE+":"+hid+","+item;
        String json = (String)redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Items> list = serviceDao.getItemsByHotelIdAndItem(hid,item);
        PageInfo pageInfo = new PageInfo(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PageInfo<Items> getItemsByHotelIdAndState(String hid,Integer state, int page, int rows) {
        String key = REDIS_ITEMS_PRE+":"+hid+","+state+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Items> list = serviceDao.getItemsByHotelIdAndState(hid,state);
        PageInfo pageInfo = new PageInfo(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PageInfo<Items> getItemsByHotelIdAndNeedLevel(String hid,Integer needLevel, int page, int rows) {
        String key = REDIS_ITEMS_PRE+":"+hid+","+needLevel+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Items> list = serviceDao.getItemsByHotelIdAndNeedLevel(hid,needLevel);
        PageInfo pageInfo = new PageInfo(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

    @Override
    public void addItems(Items items) {
        redisService.removePattern(REDIS_ITEMS_PRE+":"+items.getHid()+"*");
        serviceDao.addItems(items);
    }

    @Override
    public void updateItems(Items items) {
        redisService.removePattern(REDIS_ITEMS_PRE+":"+items.getHid()+"*");
        serviceDao.updateItems(items);
    }

    @Override
    public void deleteItems(Integer[] ids) {
        Items items = serviceDao.getItemsById(ids[0]);
        redisService.removePattern(REDIS_ITEMS_PRE+":"+items.getHid()+"*");
        for (int id : ids){
            serviceDao.deleteItems(id);
        }
    }
}
