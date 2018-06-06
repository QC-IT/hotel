package com.hotel.filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.models.Visitor;

/**
 * 
 * @author yuanhaohe
 *
 */
public class DefenseCrawlerFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(DefenseCrawlerFilter.class);
	private Map<String, Visitor> map = new ConcurrentHashMap<String, Visitor>();
	private static int rangetime;
	private static int locked;
	private static int times;

	// 初始化参数 读取web.xml配置的信息
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		rangetime = Integer.parseInt(filterConfig.getInitParameter("rangetime")) * 1000;
		locked = Integer.parseInt(filterConfig.getInitParameter("locked")) * 1000;
		times = Integer.parseInt(filterConfig.getInitParameter("times"));
		logger.debug("防御爬虫过滤器初始化成功！rangetime:" + rangetime + " locked:" + locked);
	}

	// 过滤请求
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取ip地址
		String ip = this.getRemortIP((HttpServletRequest) request);
		logger.debug("ip:" + ip + "被防御爬虫过滤器拦截");
		// 从map里面取出对应ip的游客信息
		Visitor visitor = map.get(ip);
		// 如果该ip是第一次访问，即如果不存在游客 那么就新建游客 然后把时间设置成当前时间 访问次数设置成一次
		if (visitor == null) {
			Visitor v = new Visitor();
			long time = System.currentTimeMillis();
			v.setBeginVisitTime(time);
			v.setLastVisitTime(time);
			v.setTimes(1);
			map.put(ip, v);
			// 放开过滤器
			chain.doFilter(request, response);
		} else {
			// 如果最后一次访问的时间和第一次访问的时间之差小于rangetime并且游客的访问次数过多，那么将会拦截访问拦截时间是（锁定次数+1）*locked的时间（类似网易云爬虫如果发现是爬虫那么锁定时间越来越长）
			if (visitor.getLastVisitTime() - visitor.getBeginVisitTime() < rangetime && visitor.getTimes() >= times
					&& visitor.getLastVisitTime() + locked + visitor.getRefuseTimes() * locked > System
							.currentTimeMillis()) {
				if (!visitor.getRefuse()) {
					// 如果是访问未拒绝的有课，那么久设置为拒绝访问
					visitor.setRefuse(true);
					logger.debug("ip:" + ip + "被锁定 第" + (visitor.getRefuseTimes() + 1) + "次锁定");
				}

				visitor.setLastVisitTime(System.currentTimeMillis());
			} else if (visitor.getLastVisitTime() - visitor.getBeginVisitTime() < rangetime
					&& visitor.getTimes() >= times && visitor.getLastVisitTime() + locked
							+ visitor.getRefuseTimes() * locked <= System.currentTimeMillis()) {
				// 如果锁定时间到期那么久放开锁定 同时当前有课开始访问时间和最近一次访问时间设置为当前时间
				long time = System.currentTimeMillis();
				visitor.setBeginVisitTime(time);
				visitor.setLastVisitTime(time);
				visitor.setRefuse(false);
				visitor.setTimes(1);
				logger.debug("ip:" + ip + "被解锁");
				visitor.setRefuseTimes(visitor.getRefuseTimes() + 1);
			} else {
				// 接收访问
				long time = System.currentTimeMillis();
				visitor.setLastVisitTime(time);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				visitor.setTimes(visitor.getTimes() + 1);
				logger.debug("ip:" + ip + "更新最近访问时间" + format.format(new Date(time)));
			}
			// 判断是否为锁定 锁定就直接向response流输出，如果没锁定就正常放行
			if (visitor.getRefuse()) {
				response.setContentType("application/json;charset=utf8");
				response.getWriter().write("{\"code\":401,\"msg\":\"request is refused\"}");
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {

	}

	// 获取ip地址
	private String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}
