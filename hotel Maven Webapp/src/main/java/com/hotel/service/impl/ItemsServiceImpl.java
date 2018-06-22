package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.dao.ItemsDao;
import com.hotel.models.Items;
import com.hotel.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;

    @Override
    public PageInfo<Items> getItemsByHotelId(String hid,int page, int rows) {
        //start之后 所有的查询都为分页
        PageHelper.startPage(page,rows);
        List<Items> list = itemsDao.getItemsByHotelId(hid);
        //封装成携带分页信息详细信息的数据
        for (Items items : list){
            System.out.println("---------"+items+"-----------");
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo ;
    }

    @Override
    public Items getItemsById(Integer id) {
        return itemsDao.getItemsById(id);
    }

    @Override
    public PageInfo<Items> getItemsByItem(String item, int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Items> list = itemsDao.getItemsByItem(item);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Items> getItemsByState(Integer state, int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Items> list = itemsDao.getItemsByState(state);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Items> getItemsByNeedLevel(Integer needLevel, int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Items> list = itemsDao.getItemsByNeedLevel(needLevel);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void addItems(Items items) {
        itemsDao.addItems(items);
    }

    @Override
    public void updateItems(Items items) {
        itemsDao.updateItems(items);
    }

    @Override
    public void deleteItems(Integer[] ids) {
        for (int id : ids){
            itemsDao.deleteItems(id);
        }
    }
}
