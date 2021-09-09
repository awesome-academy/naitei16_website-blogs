package com.blogs.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dao.UserDAO;
import com.blogs.dao.VerificationTokenDAO;
import com.blogs.model.User;
import com.blogs.model.VerificationToken;
import com.blogs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private VerificationTokenDAO tokenDAO;
	
	public User findById(Integer id) {
		try {
			return userDAO.getFindById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean createUser(User st) {
		try {
			userDAO.saveOrUpdate(st);
			logger.info("data insert: " + st.getId() + ", " + st.getName());
			return true;
		} catch (Exception e) {
			throw e;
		}

	}

	public boolean updateUser(User st) {
		try {
			userDAO.saveOrUpdate(st);
			logger.info("data update: " + st.getId() + ", " + st.getName());
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean deleteUser(User st) {
		try {
			logger.info("data insert: " + st.getId() + ", " + st.getName());
			userDAO.delete(st);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public UserDAO getuserDAO() {
		return userDAO;
	}

	public void setuserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Boolean isEmailAlreadyInUse(String email) {
		try {
			return userDAO.isEmailAlreadyInUse(email);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean createNewUser(User st, VerificationToken token) {
		// TODO Auto-generated method stub
		try {
			userDAO.createNewUser(st, token);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public VerificationToken getVerificationToken(String token) {
		// TODO Auto-generated method stub
		try { 
			return tokenDAO.getByToken(token);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean updateSuccessStateRegistationUser(User user, VerificationToken token) {
		try {
			return userDAO.updateSuccessStateRegistationUser(user, token);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User findUserByUsername(String username) {
		try {
			return userDAO.findUserByUsername(username);
		} catch (Exception e) {
			return null;
		}
	}
	
}
