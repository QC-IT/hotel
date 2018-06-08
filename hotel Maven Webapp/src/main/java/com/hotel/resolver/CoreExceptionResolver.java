package com.hotel.resolver;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 统一异常处理器
 * @author yuanhaohe
 *
 */
@Component
public class CoreExceptionResolver implements HandlerExceptionResolver{
private final Logger log=LoggerFactory.getLogger(CoreExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse response, Object arg2,
			Exception ex) {
		  ModelAndView mv = new ModelAndView();             
          response.setStatus(HttpStatus.OK.value()); //设置状态码  
          response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
          response.setCharacterEncoding("UTF-8"); //避免乱码  
          response.setHeader("Cache-Control", "no-cache, must-revalidate");  
          try {  
              response.getWriter().write("{\"code\":502,\"msg\":\"" + ex.getMessage() + "\"}");  
          } catch (IOException e) {  
            log.debug("发生异常:"+ex.getMessage());
          }  

          return mv;  
	}

}
