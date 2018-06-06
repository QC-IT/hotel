package com.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotel.api.service.BaiduMapService;
import com.hotel.exceptions.BaiduMapLocationFormatException;
import com.hotel.models.City;
import com.hotel.service.CityService;

/**
 * 
 * @author yuanhaohe 处理城市相关的controller
 */
@Controller
@RequestMapping("city")
public class CityController {
	private final Logger log = LoggerFactory.getLogger(CityController.class);
	@Autowired
	private BaiduMapService baiduMapService;
	@Autowired
	private CityService cityService;

	/**
	 * 根据经纬度获取当前城市位置
	 * 
	 * @author yuanhaohe
	 * @param map
	 *            经纬度
	 * @return code 错误:{"code":500} 成功:{"code":200,"city":城市名,"id":城市id}
	 * 
	 */
	@RequestMapping(value = "getLocationCity", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public @ResponseBody String getLocationCity(@RequestBody Map<String, String> map) {
		String latitude = map.get("latitude");
		String longitude = map.get("longitude");
		log.debug("latitude:" + latitude + "  longitude:" + longitude);
		if (latitude.length() != 0 || longitude.length() != 0) {
			String city = null;
			try {
				city = baiduMapService.getCityByLongitudeAndLatitude(Double.parseDouble(longitude),
						Double.parseDouble(latitude));
				
				log.debug("city:" + city);
			} catch (NumberFormatException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (BaiduMapLocationFormatException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (city != null) {
				int id=0;
				try {
					 id=cityService.selectCityIdByName(city.substring(0, city.length() - 1));
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				return "{\"code\":200,\"city\":\"" + city.substring(0, city.length() - 1) + "\",\"id\":"+id+"}";
			} else
				return "{\"code\":500}";
		} else
			return "{\"code\":500}";

	}

	/**
	 * 查找热门城市 前期先直接读取数据库 后期改为读取redis
	 * 
	 * @author yuanhaohe
	 * @return 错误:"{"code":500}",成功:"{"code":200,"citys":城市列表}"
	 */
	@RequestMapping(value = "findHotCity.json", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public @ResponseBody String findHotCity(HttpServletRequest request) {
		HttpSession session=request.getSession();
		List<City> citys = null;
		try {
			citys = cityService.getHotCityList();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		if (citys != null) {
			JSONObject json = new JSONObject();
			json.put("code", 200);
			json.put("citys", citys);
			return json.toJSONString();
		} else
			return "{\"code\":500}";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "allCity", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public @ResponseBody String getAllCity() {
		List<City> allcitys = null;
		try {
			allcitys = cityService.getAllCityList();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
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
			for (City city : allcitys) {
				if (city.getAbbr().charAt(0) == 'A') {
					key.add("A");
					A.add(city);
				} else if (city.getAbbr().charAt(0) == 'B') {
					key.add("B");
					B.add(city);
				} else if (city.getAbbr().charAt(0) == 'C') {
					key.add("C");
					C.add(city);
				} else if (city.getAbbr().charAt(0) == 'D') {
					key.add("D");
					D.add(city);
				} else if (city.getAbbr().charAt(0) == 'E') {
					key.add("E");
					E.add(city);
				} else if (city.getAbbr().charAt(0) == 'F') {
					key.add("F");
					F.add(city);
				} else if (city.getAbbr().charAt(0) == 'G') {
					key.add("G");
					G.add(city);
				} else if (city.getAbbr().charAt(0) == 'H') {
					key.add("H");
					H.add(city);
				} else if (city.getAbbr().charAt(0) == 'I') {
					key.add("I");
					I.add(city);
				} else if (city.getAbbr().charAt(0) == 'J') {
					key.add("J");
					J.add(city);
				} else if (city.getAbbr().charAt(0) == 'K') {
					key.add("K");
					K.add(city);
				} else if (city.getAbbr().charAt(0) == 'L') {
					key.add("L");
					L.add(city);
				} else if (city.getAbbr().charAt(0) == 'M') {
					key.add("M");
					M.add(city);
				} else if (city.getAbbr().charAt(0) == 'N') {
					key.add("N");
					N.add(city);
				} else if (city.getAbbr().charAt(0) == 'O') {
					key.add("O");
					O.add(city);
				} else if (city.getAbbr().charAt(0) == 'P') {
					key.add("P");
					P.add(city);
				} else if (city.getAbbr().charAt(0) == 'Q') {
					key.add("Q");
					Q.add(city);
				} else if (city.getAbbr().charAt(0) == 'R') {
					key.add("R");
					R.add(city);
				} else if (city.getAbbr().charAt(0) == 'S') {
					key.add("S");
					S.add(city);
				} else if (city.getAbbr().charAt(0) == 'T') {
					key.add("T");
					T.add(city);
				} else if (city.getAbbr().charAt(0) == 'U') {
					key.add("U");
					U.add(city);
				} else if (city.getAbbr().charAt(0) == 'V') {
					key.add("V");
					V.add(city);
				} else if (city.getAbbr().charAt(0) == 'W') {
					key.add("W");
					W.add(city);
				} else if (city.getAbbr().charAt(0) == 'X') {
					key.add("X");
					X.add(city);
				} else if (city.getAbbr().charAt(0) == 'Y') {
					key.add("Y");
					Y.add(city);
				} else {
					key.add("Z");
					Z.add(city);
				}

			}
			JSONObject json = new JSONObject();
			if (A.size() != 0) {
				json.put("A", A);
			} 
			if (B.size() != 0) {
				json.put("B", B);
			} 
			if (C.size() != 0) {
				json.put("C", C);
			} 
			if (D.size() != 0) {
				json.put("D", D);
			} 
			if (E.size() != 0) {
				json.put("E", E);
			} 
			if (F.size() != 0) {
				json.put("F", F);
			} 
			if (G.size() != 0) {
				json.put("G", G);
			} 
			if (H.size() != 0) {
				json.put("H", H);
			} 
			if (I.size() != 0) {
				json.put("I", I);
			} 
			if (J.size() != 0) {
				json.put("J", J);
			} 
			if (K.size() != 0) {
				json.put("K", K);
			} 
			if (L.size() != 0) {
				json.put("L", L);
			} 
			if (M.size() != 0) {
				json.put("M", M);
			} 
			if (N.size() != 0) {
				json.put("N", N);
			} 
			if (O.size() != 0) {
				json.put("O", O);
			} 
			if (P.size() != 0) {
				json.put("P", P);
			} 
			if (Q.size() != 0) {
				json.put("Q", Q);
			} 
			if (R.size() != 0) {
				json.put("R", R);
			} 
			if (S.size() != 0) {
				json.put("S", S);
			} 
			if (T.size() != 0) {
				json.put("T", T);
			} 
			if (U.size() != 0) {
				json.put("U", U);
			} 
			if (V.size() != 0) {
				json.put("V", V);
			} 
			if (W.size() != 0) {
				json.put("W", W);
			} 
			if (X.size() != 0) {
				json.put("X", X);
			} 
			if (Y.size() != 0) {
				json.put("Y", Y);
			} 
			if (Z.size() != 0) {
				json.put("Z", Z);
			}
			json.put("code", 200);
			json.put("key", key);
			return json.toJSONString();
		} else
			return "{\"code\":500}";

	}
}
