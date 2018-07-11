package com.hotel.listener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hotel.quartz.QuartzManager;
import com.hotel.util.DateUtil;





/**
 * 容器监听器
 * @author jing
 */
public class QuartzJobListener implements ServletContextListener {
private final static Logger logger=LoggerFactory.getLogger(QuartzJobListener.class);
    public void contextInitialized(ServletContextEvent arg0) {
        /***处理获取数据库的job表，然后遍历循环每个加到job中 ***/
        QuartzManager quartzManager = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(QuartzManager.class);
        //此处就不写获取数据库了，模拟一个集合遍历的数据  
        List<Map<String,Object>> listMap=new ArrayList<>();
        Map<String, Object> map1=new HashMap<String, Object>();
        map1.put("jobClass","com.hotel.quartz.EmailQuartz");
        map1.put("jobName","email");
        map1.put("jobGroupName","email");
        map1.put("jobTime","0 0 1 * * ?");
        listMap.add(map1);

        for (Map<String, Object> map : listMap) {
            try {
                quartzManager.addJob((Class<? extends Job>)(Class.forName((String)map1.get("jobClass"))),(String)map.get("jobName"), (String)map.get("jobGroupName"),(String)map.get("jobTime"));
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        logger.debug(DateUtil.dateFormatToString(new Date())+"quartzJob监听已经启动");
    }
    public void contextDestroyed(ServletContextEvent arg0) {
    }
    }