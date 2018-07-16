package com.hotel.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.hotel.models.Items;
import com.hotel.models.Result;
import com.hotel.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;

@Controller
@RequestMapping("/items")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    /**
     * 根据酒店ID获取服务
     * @return
     */
    @RequestMapping(value = "getItemsByHotelId.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result getItemsByHotelId(@RequestBody HashMap<String,Object> map){
        String hid = (String) map.get("hotelId");
        Integer page = (Integer)map.get("page");
        Integer rows = (Integer)map.get("rows");
        System.out.println("hid:" + hid +"page :" +page +" rows:"+rows);
        if (StringUtil.isNotEmpty(hid)&&page >= 0 && rows >0){
            PageInfo<Items> pageInfo = serviceService.getItemsByHotelId(hid, page, rows);
            return Result.success("查询成功",pageInfo.getList());
        }else {
            return Result.fail("提交数据错误");
        }
    }

    /**
     * 根据服务ID获取服务
     * @return
     */
    @RequestMapping(value = "getItemsById.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result getItemsById(@RequestBody HashMap<String,Integer> map){
        Integer id = map.get("id");
        if (id >=0){
            Items items = serviceService.getItemsById(id);
            return Result.success("查询成功",items);
        }else {
            return Result.fail("传输数据错误");
        }
    }

    /**
     * 根据服务项目查询服务
     * @return
     */
    @RequestMapping(value = "getItemsByHotelIdAndItem.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result getItemsByHotelIdAndItem(@RequestBody HashMap<String,Object> map){
        String hid = (String)map.get("hotelId");
        String item = (String)map.get("item");
        Integer page = (Integer)map.get("page");
        Integer rows = (Integer)map.get("rows");

        if (StringUtil.isNotEmpty(hid)&&StringUtil.isNotEmpty(item)&&page>=0&&rows>0){
            PageInfo<Items> pageInfo = serviceService.getItemsByHotelIdAndItem(hid, item, page, rows);
            return Result.success("查询成功",pageInfo.getList());
        }else {
            return Result.fail("传输数据错误");
        }
    }

    /**
     * 根据状态查询服务
     * @return
     */
    @RequestMapping(value = "getItemsByHotelIdAndState.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result getItemsByHotelIdAndState(@RequestBody HashMap<String,Object> map){
        String hid = (String)map.get("hotelId");
        Integer state = (Integer)map.get("state");
        Integer page = (Integer) map.get("page");
        Integer rows = (Integer) map.get("rows");
        if (StringUtil.isNotEmpty(hid)&&page>=0&&rows>0){
            PageInfo<Items> pageInfo = serviceService.getItemsByHotelIdAndState(hid, state, page, rows);
            return Result.success("查询成功",pageInfo.getList());
        }else {
            return Result.fail("数据传输错误");
        }
    }

    /**
     * 根据需要的VIP等级查询服务
     * @return
     */
    @RequestMapping(value = "getItemsByHotelIdAndNeedLevel.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result getItemsByHotelIdAndNeedLevel(@RequestBody HashMap<String,Object> map){
        String hid = (String)map.get("hotelId");
        Integer needLevel = (Integer)map.get("needLevel");
        Integer page = (Integer) map.get("page");
        Integer rows = (Integer) map.get("rows");
        if (StringUtil.isNotEmpty(hid)&&page>=0&&rows>0){
            PageInfo<Items> pageInfo = serviceService.getItemsByHotelIdAndNeedLevel(hid, needLevel, page, rows);
            return Result.success("查询成功",pageInfo.getList());
        }else {
            return Result.fail("数据传输错误");
        }
    }

    /**
     * 添加服务
     */
    @RequestMapping(value = "addItems.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result addItems(@RequestBody Items items){
        serviceService.addItems(items);
        return Result.success("添加成功",null);
    }

    /**
     * 修改服务
     * @param items
     */
    @RequestMapping(value = "updateItems.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result updateItems(@RequestBody Items items){
        serviceService.updateItems(items);
        return Result.success("修改成功",null);
    }

    @RequestMapping(value = "deleteItems.json",produces="application/json;charset=utf8")
    @ResponseBody
    public Result deleteItems(@RequestBody Integer[] ids){
        serviceService.deleteItems(ids);
        return Result.success("删除成功",null);
    }

}
