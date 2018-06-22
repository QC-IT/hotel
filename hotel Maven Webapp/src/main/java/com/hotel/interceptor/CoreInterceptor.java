package com.hotel.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.util.PropertiesUtil;
/**
 * 核心拦截器主要是拦截和管理用户访问资源权限
 * @author yuanhaohe
 */
public class CoreInterceptor implements HandlerInterceptor{
private final Logger log=LoggerFactory.getLogger(CoreInterceptor.class);
private static Map<String,String> anonymous;
private static Map<String,String> needLogin;

static {
	anonymous=PropertiesUtil.getKeyAndValueMap(CoreInterceptor.class.getClassLoader().getResource("/").getFile()+"properties/anonyous.properties");
	needLogin=PropertiesUtil.getKeyAndValueMap(CoreInterceptor.class.getClassLoader().getResource("/").getFile()+"properties/needlogin.properties");
}

@Override
public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
		throws Exception {
	
}

@Override
public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
		throws Exception {

	
}

/**
 * @author yuanhaohe
 * 配置完匿名权限的地址将允许所有用户访问，也可以在needlogin中配置地址（配置后用户需要登录后才能访问，否则返回code 400）配置信息详见properties
 * 注:配置完匿名用户地址权限后，将会覆盖有名用户权限（所有URL必须在其中一个配置文件中配置，否则会被拦截）展示在session里存了user表示为登录，到时候再商定
 * @return 是否执行controller
 */
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
	Set<String> anonymousKey=anonymous.keySet();
	//允许匿名用户获取资源
	for(String key:anonymousKey){
		if(request.getRequestURI().contains(key)&&anonymous.get(key).equals("true")){
			log.debug("匿名访问:"+request.getRequestURI());
			return true;
		}
	}
	//允许登录后的用户获取资源
	Set<String> needLoginKey=needLogin.keySet();
	for(String key:needLoginKey){
		if(request.getRequestURI().contains(key)&&needLogin.get(key).equals("true")){
			HttpSession session=request.getSession();
			String openId=(String) session.getAttribute("openId");
			if(openId!=null){
				log.debug("实名访问:"+request.getRequestURI()+"  用户id:"+openId);
				return true;
			}
		}
	}
    response.setStatus(HttpStatus.OK.value()); //设置状态码  
	response.setContentType("application/json;charset=utf-8");
	response.getWriter().write("{\"code\":400,\"msg\":\"No Aythority\"}");
	log.debug("访问被拒绝:"+request.getRequestURI());
	return false;
}

}
