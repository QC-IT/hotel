package com.hotel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 通过properties返回map对象的工具类（终类）
 * @author yuanhaohe
 *
 */
public final class PropertiesUtil {
	/**
	 * 返回properties中的内容
	 * @param filePath properties的路径
	 * @return  线程安全的map
	 */
public static 	 Map<String,String> getKeyAndValueMap(String filePath){
	return getKeyAndValueMap(new File(filePath));
}
/**
 * 返回properties中的内容
 * @param file properties文件
 * @return  线程安全的map
 */
public static Map<String,String> getKeyAndValueMap(File file){
	InputStream in=null;
try {
	 in=new FileInputStream(file);
	 return getKeyAndValueMap(in);
} catch (FileNotFoundException e) {
	// TODO 自动生成的 catch 块
	e.printStackTrace();
}finally{
	
	if(in!=null){
		try {
			in.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
return null;

}
/**
 * 返回properties中的内容
 * @param in properties输入流
 * @return 线程安全的map
 */
public static Map<String,String> getKeyAndValueMap(InputStream in){
	Map<String,String> result=null;
	if(in!=null){
	Properties properties=new Properties();
	result=new ConcurrentHashMap<String, String>();
	try {
		properties.load(in);
		Set<Object> keys=properties.keySet();
		for(Object key:keys){
			result.put(key.toString(), properties.getProperty((String) key));
		}
	} catch (FileNotFoundException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally{
	if(in!=null){
		try {
			in.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	}
	return result;
	}else {
		return result;
		}
}

}
