package com.blogs.daoimpl;

import com.blogs.dao.GenericDAO;
import com.blogs.dao.UserDAO;
import com.blogs.model.User;

@SuppressWarnings("serial")
public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
}
