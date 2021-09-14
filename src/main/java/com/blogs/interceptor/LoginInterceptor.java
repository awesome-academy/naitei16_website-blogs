package com.blogs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import com.blogs.controller.UserController;

public class LoginInterceptor implements HandlerInterceptor {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String username = null;
		if (request.getUserPrincipal() != null) {
			username = request.getUserPrincipal().getName();
		}
		logger.info("username : " + username);
		return true;
	}
}
