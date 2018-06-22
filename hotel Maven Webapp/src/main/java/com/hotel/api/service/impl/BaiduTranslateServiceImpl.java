package com.hotel.api.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hotel.api.service.BaiduTranslateService;
import com.hotel.util.HttpClientUtil;
import com.hotel.util.MD5Util;

@Service
public class BaiduTranslateServiceImpl implements BaiduTranslateService {
	@Value("${Baidu_translate_AppId}")
	private String appId;
	@Value("${Baidu_translate_appSecret}")
	private String appSecret;
	private String resolve(String str) {
JSONObject json=JSON.parseObject(str);
JSONArray array=json.getJSONArray("trans_result");
json=(JSONObject) array.get(0);
		return json.getString("dst");
	}

	@Override
	public String translateToEnglish(String from) {
		return translate(from, BaiduTranslateService.ENGLISH);
	}

	public String translate(String from, String toLanguage) {
		int salt=(int) (Math.random()*100);
	String sign=MD5Util.md5Encrypt(appId+from+salt+appSecret, 1);
		String result = HttpClientUtil
				.sendGet("http://api.fanyi.baidu.com/api/trans/vip/translate?from=auto&to="+toLanguage+"&appid="+appId+"&salt="+salt+"&sign="
						+sign+"&q="+from, null);
		return resolve(result);
	}


	@Override
	public String translateToChinese(String from) {
		return translate(from, BaiduTranslateService.CHINESE);
	}

	@Override
	public String translateToJapanese(String from) {
	
		 return translate(from, BaiduTranslateService.JAPANESE);
	}

	

}
