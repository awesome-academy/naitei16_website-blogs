package com.blogs.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogs.controller.UserController;
import com.blogs.dao.UserDAO;
import com.blogs.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = Logger.getLogger(MyUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("login doing auth...");
		com.blogs.model.User user = userService.findUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
		if(user.getRole()!=null) {
			logger.info("role of user : " + user.getRole().toString());
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user.getRole().toString());
			grantList.add(authority);
		}

		return (UserDetails) new User(user.getUsername(), user.getPassword(), true, true, true,user.isConfirmed(),grantList);
	}

}
