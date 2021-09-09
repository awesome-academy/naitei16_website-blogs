package com.blogs.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blogs.model.User;
import com.blogs.pojo.Relationship;
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
	
	@RequestMapping(value = "/u/{username}")
	public ModelAndView getUsers(@PathVariable String username) {
		User user = userService.findUserByUsername(username, true);
		ModelAndView model = new ModelAndView("users/userInfoPage");
		//check: the logged user i follow this user
		System.out.print(user);
		if(ControllerUtils.getPrincipal().getId().intValue()!=user.getId().intValue()) {
			boolean hasFollowed = userService.checkUserFollowUser(ControllerUtils.getPrincipal().getId(), user.getId());
			model.addObject("hasFollowed", hasFollowed);
		}
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value = "/api/follow", method = RequestMethod.POST, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Relationship> follow(@RequestBody Relationship relationship) {		
		Integer follower_id = relationship.getFollower_id();
		Integer followed_id = relationship.getFollowed_id();
		String type = relationship.getType();
		userService.updateFollowUser(follower_id, followed_id, type);
		
		logger.info("process relationship done.");
		return new ResponseEntity<Relationship>(relationship, HttpStatus.OK);
	}
	
}
