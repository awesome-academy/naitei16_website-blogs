package com.blogs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ControllerUtils {
	static String baseUrlOf(HttpServletRequest request) {
		return ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
	}
	
	static boolean isLogined() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth==null || auth instanceof AnonymousAuthenticationToken) return false;
		return true;
	}
	

}
