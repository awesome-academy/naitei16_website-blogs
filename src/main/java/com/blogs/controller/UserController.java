package com.blogs.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blogs.model.User;
import com.blogs.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView index() {
		logger.info("home page");
		ModelAndView model = new ModelAndView("users/index");
		
		User user = new User("thanhan1181999","Nguyen Thanh An", 1);
//		User user1 = new User("thuythu1999","Nguyen Thi Thu Thuy", 0);
//		userService.createUser(user);
//		userService.createUser(user1);
		
		model.addObject("user", user);
		return model;
	}
	
}
