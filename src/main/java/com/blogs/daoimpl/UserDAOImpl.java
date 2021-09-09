package com.blogs.daoimpl;

import org.hibernate.query.Query;

import com.blogs.dao.GenericDAO;
import com.blogs.dao.UserDAO;
import com.blogs.model.User;
import com.blogs.model.VerificationToken;

@SuppressWarnings("serial")
public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public Boolean isEmailAlreadyInUse(String email) {
		String hql = "SELECT count(*) FROM User U WHERE U.email = :email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", email);
		Long result = (Long) query.uniqueResult();
		return result.intValue() == 1;
	}

	@Override
	public boolean updateSuccessStateRegistationUser(User user, VerificationToken token) {
		getSession().saveOrUpdate(user);
		getSession().delete(token);
		return true;
	}

	@Override
	public boolean createNewUser(User user, VerificationToken token) {
		getSession().saveOrUpdate(user);
		getSession().saveOrUpdate(token);
		return true;
	}

	@Override
	public User findUserByUsername(String username) {
		String hql = "FROM User U WHERE U.username = :username";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", username);
		User user = (User) query.uniqueResult();
		return user;
	}

}
