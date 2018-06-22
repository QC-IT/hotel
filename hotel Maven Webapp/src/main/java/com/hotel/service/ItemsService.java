package com.hotel.service;

import com.github.pagehelper.PageInfo;
import com.hotel.models.Items;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务项目service 所有查询均已分页实现,page为当前页码，rows为每页的数量
 */

public interface ItemsService {
     /**
     * 根据酒店ID获取服务
     * @return
     */
    public PageInfo<Items> getItemsByHotelId(String hid, int page, int rows);

    /**
     * 根据服务ID获取服务
     * @return
     */
    public Items getItemsById(Integer id);

    /**
     * 根据服务项目查询服务
     * @return
     */
    public PageInfo<Items> getItemsByItem(String item,int page, int rows);

    /**
     * 根据状态查询服务
     * @return
     */
    public PageInfo<Items> getItemsByState(Integer state,int page, int rows);

    /**
     * 根据需要的VIP等级查询服务
     * @return
     */
    public PageInfo<Items> getItemsByNeedLevel(Integer needLevel,int page, int rows);

    /**
     * 添加服务
     */
    public void addItems(Items items);

    /**
     * 修改服务
     * @param items
     */
    public void updateItems(Items items);

    /**
     * 根据id删除服务，可批量
     * @param ids 传入的ID列表
     */
    public void deleteItems(Integer[] ids);
}
