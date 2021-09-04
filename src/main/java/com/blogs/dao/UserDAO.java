package com.blogs.dao;

import com.blogs.model.User;
import com.blogs.model.VerificationToken;

public interface UserDAO extends IGenericDAO<Integer, User> {

	Boolean isEmailAlreadyInUse(String email);
	boolean updateSuccessStateRegistationUser(User user, VerificationToken token);
	boolean createNewUser(User st, VerificationToken token);
	
}
