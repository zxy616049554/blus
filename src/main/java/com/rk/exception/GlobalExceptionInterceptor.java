package com.rk.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

public class GlobalExceptionInterceptor implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Logger log = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);
		ModelAndView mv = new ModelAndView();
		/* 使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常 */
		FastJsonJsonView view = new FastJsonJsonView();
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("statusCode", "1000001");
		attributes.put("statusMessage", ex.getMessage());
		view.setAttributesMap(attributes);
		mv.setView(view);
		log.debug("异常:" + ex.getMessage(), ex);
		return mv;
	}
}
