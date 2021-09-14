package com.blogs.service;

import com.blogs.model.User;
import com.blogs.model.VerificationToken;

public interface UserService{
	User findUserByUsername(String username);
	User findById(Integer id);
	boolean createUser(User st);
	boolean updateUser(User st);
	boolean deleteUser(User st);
	Boolean isEmailAlreadyInUse(String email);
	boolean createNewUser(User st, VerificationToken token);
	
	// get verify token of user
	VerificationToken getVerificationToken(String token); 
	
	// set user.isConfirmed = true AND delete verify token
	boolean updateSuccessStateRegistationUser(User user, VerificationToken token); 
}
