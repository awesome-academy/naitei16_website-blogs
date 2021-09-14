package com.blogs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blogs.pojo.MyUserDetails;

public class ControllerUtils {
	static String baseUrlOf(HttpServletRequest request) {
		return ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
	}
	
	static boolean isLogined() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth==null || auth instanceof AnonymousAuthenticationToken) return false;
		return true;
	}
	
	static MyUserDetails getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			System.out.print("have printcapal");
			return (MyUserDetails) principal;
		}
		return null;
	}

}
