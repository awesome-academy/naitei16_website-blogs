package com.blogs.dao;

import com.blogs.model.VerificationToken;

public interface VerificationTokenDAO extends IGenericDAO<Integer, VerificationToken>{
	VerificationToken getByToken(String token);
}
