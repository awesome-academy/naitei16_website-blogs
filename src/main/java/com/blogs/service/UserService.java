package com.blogs.service;

import com.blogs.model.User;

public interface UserService{
	User findById(Integer id);
	boolean createUser(User st);
	boolean updateUser(User st);
	boolean deleteUser(User st);
}
