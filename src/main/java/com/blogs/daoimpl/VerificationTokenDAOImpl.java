package com.blogs.daoimpl;

import org.hibernate.query.Query;

import com.blogs.dao.GenericDAO;
import com.blogs.dao.VerificationTokenDAO;
import com.blogs.model.VerificationToken;

@SuppressWarnings("serial")
public class VerificationTokenDAOImpl extends GenericDAO<Integer, VerificationToken> implements VerificationTokenDAO {

	@SuppressWarnings("unchecked")
	@Override
	public VerificationToken getByToken(String token) {
		String hql = "from VerificationToken v where v.token = :token ";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(1);
		query.setParameter("token", token);
		Object result = query.getSingleResult();
		if (result == null)
			return null;
		return (VerificationToken) result;
	}
}
