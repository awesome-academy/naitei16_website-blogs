package com.blogs.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.blogs.dao.UserDAO;
import com.blogs.model.User;
import com.blogs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	UserDAO userDAO;

	public User findById(Integer id) {
		return userDAO.getFindById(id);
	}

	public boolean createUser(User st) {
		try {
			userDAO.saveOrUpdate(st);
			logger.info("data insert: " + st.getId() + ", " + st.getName());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean updateUser(User st) {
		try {
			userDAO.saveOrUpdate(st);
			logger.info("data update: " + st.getId() + ", " + st.getName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUser(User st) {
		try {
			logger.info("data insert: " + st.getId() + ", " + st.getName());
			userDAO.delete(st);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public UserDAO getuserDAO() {
		return userDAO;
	}

	public void setuserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
