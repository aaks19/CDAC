package com.healthcare.dao;

import com.healthcare.entities.User;
import org.hibernate.*;

import static com.healthcare.utils.HibernateUtils.getFactory;

public class UserDaoImpl implements UserDao {
	
	@Override
	public String signup(User newUser) {
		
		// 1. Get session from SessionFactory
		Session session = getFactory().getCurrentSession();
		
		// 2. Begin a Transaction 
		Transaction tx = session.beginTransaction();
		
		// 3. Open try-catch block
		try {
			// Session API for inserting a record
			session.persist(newUser);
			tx.commit();
		}catch(RuntimeException e) {
			if(tx != null) {
				tx.rollback(); // if transaction is not null then rollback and discard transaction tx
			}
			throw e;
		}
		return "User Registered Successfully... with ID = " + newUser.getId();
	}

}
