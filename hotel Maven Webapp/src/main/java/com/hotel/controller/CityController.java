package com.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotel.api.service.BaiduMapService;
import com.hotel.exceptions.BaiduMapLocationFormatException;
import com.hotel.models.City;
import com.hotel.service.CityService;

/**
 * @author yuanhaohe 处理城市相关的controller
 */
@Controller
@RequestMapping("city")
public class CityController {
	private static final Logger logger = LoggerFactory.getLogger(CityController.class);
	@Autowired
	private BaiduMapService baiduMapService;
	@Autowired
	private CityService cityService;

	/**
	 * 根据经纬度获取当前城市位置
	 * @author yuanhaohe
	 * @param map
	 *            经纬度
	 * @return code 错误:{\"code\":500,\"msg\":\"获取城市失败\"}; 成功:{"code":200,"city":城市名,"citycode":城市code}
	 */
	@RequestMapping(value = "getLocationCity.json", produces = "application/json;charset=utf8")
	public @ResponseBody String getLocationCity(@RequestBody Map<String, String> map) {
		String latitude = map.get("latitude");
		String longitude = map.get("longitude");
		logger.debug("latitude:" + latitude + "  longitude:" + longitude);
		if (latitude.length() != 0 || longitude.length() != 0) {
			String city = null;
			try {
				city = baiduMapService.getCityByLongitudeAndLatitude(Double.parseDouble(longitude),
						Double.parseDouble(latitude));
				logger.debug("city:" + city);
			} catch (NumberFormatException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (BaiduMapLocationFormatException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (city != null) {
				String code=null;
				try {
					 code=cityService.getCodeByCityName(city.substring(0, city.length() - 1));
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				JSONObject json=new JSONObject();
				json.put("code", "200");
				Map<String,String> data=new HashMap<String,String>();
				data.put("city", city.substring(0, city.length() - 1));
				data.put("citycode",code);
				json.put("data", data);
				return json.toJSONString();
			} else
				return "{\"code\":500,\"msg\":\"获取城市失败\"}";
		} else
			return "{\"code\":500,\"msg\":\"获取城市失败\"}";
	}

	
	/**
	 * 查找热门城市 前期先直接读取数据库 后期改为读取redis
	 * @author yuanhaohe
	 * @return 错误:{\"code\":500,\"msg\":\"服务器未知错误\"},成功:"{"code":200,"citys":城市列表}"
	 */
	@RequestMapping(value = "findHotCity.json", produces = "application/json;charset=utf8")
	public @ResponseBody String findHotCity(HttpServletRequest request) {
		List<City> citys = null;
		try {
			citys = cityService.getHotCityList();
			JSONObject json = new JSONObject();
			json.put("code", 200);
			Map<String,Object> data=new HashMap<String, Object>();
			data.put("citys", citys);
			json.put("data", data);
			return json.toJSONString();
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"服务器未知错误\"}";
		}
		
		
	}

	
	/**
	 *获取全部城市信息 
	 * @return 所有城市
	 */
	@RequestMapping(value = "allCity.json", produces = "application/json;charset=utf8")
	public @ResponseBody String getAllCity() {
		List<City> allcitys = null;
		try {
			allcitys = cityService.getAllCityList();
			Set<String> key = new TreeSet<String>();
			if (allcitys != null) {
				List<City> A = new ArrayList<City>();
				List<City> B = new ArrayList<City>();
				List<City> C = new ArrayList<City>();
				List<City> D = new ArrayList<City>();
				List<City> E = new ArrayList<City>();
				List<City> F = new ArrayList<City>();
				List<City> G = new ArrayList<City>();
				List<City> H = new ArrayList<City>();
				List<City> I = new ArrayList<City>();
				List<City> J = new ArrayList<City>();
				List<City> K = new ArrayList<City>();
				List<City> L = new ArrayList<City>();
				List<City> M = new ArrayList<City>();
				List<City> N = new ArrayList<City>();
				List<City> O = new ArrayList<City>();
				List<City> P = new ArrayList<City>();
				List<City> Q = new ArrayList<City>();
				List<City> R = new ArrayList<City>();
				List<City> S = new ArrayList<City>();
				List<City> T = new ArrayList<City>();
				List<City> U = new ArrayList<City>();
				List<City> V = new ArrayList<City>();
				List<City> W = new ArrayList<City>();
				List<City> X = new ArrayList<City>();
				List<City> Y = new ArrayList<City>();
				List<City> Z = new ArrayList<City>();
				Map<String,Object> data=new HashMap<String, Object>();
				for (City city : allcitys) {
					String abbr=city.getAbbr();
					if (abbr.charAt(0) == 'A') {
						key.add("A");
						A.add(city);
					} else if (abbr.charAt(0) == 'B') {
						key.add("B");
						B.add(city);
					} else if (abbr.charAt(0) == 'C') {
						key.add("C");
						C.add(city);
					} else if (abbr.charAt(0) == 'D') {
						key.add("D");
						D.add(city);
					} else if (abbr.charAt(0) == 'E') {
						key.add("E");
						E.add(city);
					} else if (abbr.charAt(0) == 'F') {
						key.add("F");
						F.add(city);
					} else if (abbr.charAt(0) == 'G') {
						key.add("G");
						G.add(city);
					} else if (abbr.charAt(0) == 'H') {
						key.add("H");
						H.add(city);
					} else if (abbr.charAt(0) == 'I') {
						key.add("I");
						I.add(city);
					} else if (abbr.charAt(0) == 'J') {
						key.add("J");
						J.add(city);
					} else if (abbr.charAt(0) == 'K') {
						key.add("K");
						K.add(city);
					} else if (abbr.charAt(0) == 'L') {
						key.add("L");
						L.add(city);
					} else if (abbr.charAt(0) == 'M') {
						key.add("M");
						M.add(city);
					} else if (abbr.charAt(0) == 'N') {
						key.add("N");
						N.add(city);
					} else if (abbr.charAt(0) == 'O') {
						key.add("O");
						O.add(city);
					} else if (abbr.charAt(0) == 'P') {
						key.add("P");
						P.add(city);
					} else if (abbr.charAt(0) == 'Q') {
						key.add("Q");
						Q.add(city);
					} else if (abbr.charAt(0) == 'R') {
						key.add("R");
						R.add(city);
					} else if (abbr.charAt(0) == 'S') {
						key.add("S");
						S.add(city);
					} else if (abbr.charAt(0) == 'T') {
						key.add("T");
						T.add(city);
					} else if (abbr.charAt(0) == 'U') {
						key.add("U");
						U.add(city);
					} else if (abbr.charAt(0) == 'V') {
						key.add("V");
						V.add(city);
					} else if (abbr.charAt(0) == 'W') {
						key.add("W");
						W.add(city);
					} else if (abbr.charAt(0) == 'X') {
						key.add("X");
						X.add(city);
					} else if (abbr.charAt(0) == 'Y') {
						key.add("Y");
						Y.add(city);
					} else {
						key.add("Z");
						Z.add(city);
					}
				}
				JSONObject json = new JSONObject();
				if (A.size() != 0) {
					data.put("A", A);
				} 
				if (B.size() != 0) {
					data.put("B", B);
				} 
				if (C.size() != 0) {
					data.put("C", C);
				} 
				if (D.size() != 0) {
					data.put("D", D);
				} 
				if (E.size() != 0) {
					data.put("E", E);
				} 
				if (F.size() != 0) {
					data.put("F", F);
				} 
				if (G.size() != 0) {
					data.put("G", G);
				} 
				if (H.size() != 0) {
					data.put("H", H);
				} 
				if (I.size() != 0) {
					data.put("I", I);
				} 
				if (J.size() != 0) {
					data.put("J", J);
				} 
				if (K.size() != 0) {
					data.put("K", K);
				} 
				if (L.size() != 0) {
					data.put("L", L);
				} 
				if (M.size() != 0) {
					data.put("M", M);
				} 
				if (N.size() != 0) {
					data.put("N", N);
				} 
				if (O.size() != 0) {
					data.put("O", O);
				} 
				if (P.size() != 0) {
					data.put("P", P);
				} 
				if (Q.size() != 0) {
					data.put("Q", Q);
				} 
				if (R.size() != 0) {
					data.put("R", R);
				} 
				if (S.size() != 0) {
					data.put("S", S);
				} 
				if (T.size() != 0) {
					data.put("T", T);
				} 
				if (U.size() != 0) {
					data.put("U", U);
				} 
				if (V.size() != 0) {
					data.put("V", V);
				} 
				if (W.size() != 0) {
					data.put("W", W);
				} 
				if (X.size() != 0) {
					data.put("X", X);
				} 
				if (Y.size() != 0) {
					data.put("Y", Y);
				} 
				if (Z.size() != 0) {
					data.put("Z", Z);
				}
				json.put("code", 200);
				data.put("key", key);
				json.put("data", data);
				return json.toJSONString();
				}else{
					return "{\"code\":500,\"msg\":\"获取城市列表失败\"}";
				}
		} catch (Exception e) {
			return "{\"code\":500,\"msg\"\"获取城市列表失败\"}";
		}
		
		
		
	}
	/**
	 * 添加城市
	 * @param city 
	 * @return
	 */
	@RequestMapping(value="addCity.json",produces="application/json;charset=utf8")
	public@ResponseBody
	String addCityList(@RequestBody City city){
	//向集合中添加城市
		try {
			cityService.insertCity(city);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"code\":500,\"msg\":\"服务器异常\"}";
		}
		return "{\"code\":200,\"msg\":\"success\"}";
	}
	
	/**
	 * 通过城市id删除城市
	 * @param map
	 * @return
	 */
	@RequestMapping(value="deleteCity.json",produces="application/json;charset=utf8")
	public @ResponseBody
	String deleteCityList(@RequestBody Map<String,String> map){
		try {
			cityService.deleteCity(map.get("id"));
		} catch (Exception e) {
			return "{\"code\":500,\"msg\":\"服务器异常\"}";
		}
		return "{\"code\":200,\"msg\":\"success\"}";
	}
	
}
