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

public final class PropertiesUtil {
	
public static 	 Map<String,String> getKeyAndValueMap(String filePath){
	return getKeyAndValueMap(new File(filePath));
}
	
public static Map<String,String> getKeyAndValueMap(File file){
try {
	return getKeyAndValueMap(new FileInputStream(file));
} catch (FileNotFoundException e) {
	// TODO 自动生成的 catch 块
	e.printStackTrace();
}
return null;
}

public static Map<String,String> getKeyAndValueMap(InputStream in){
	Properties properties=new Properties();
	Map<String,String> result=new HashMap<String, String>();
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
}

}