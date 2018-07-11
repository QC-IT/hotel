package com.hotel.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil {
	private static String dateFormat;
	private static final Logger logger=LoggerFactory.getLogger(DateUtil.class);
	static {
		Properties p=new Properties();
		try {
			p.load(new FileInputStream(DateUtil.class.getClassLoader().getResource("/").getPath()+"properties/system.properties"));
			dateFormat=p.getProperty("system_dataformat");
			logger.debug(dateFormat);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
public static String dateFormatToString(Date date){
	SimpleDateFormat format=new SimpleDateFormat();
	return format.format(date);
}
}
