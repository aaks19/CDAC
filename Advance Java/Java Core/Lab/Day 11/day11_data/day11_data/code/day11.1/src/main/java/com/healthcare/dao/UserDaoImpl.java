package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public class UserDaoImpl implements UserDao {

	@Override
	public String signUp(User newUser) {
		// newUser - TRANSIENT
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();// new Session=> new L1 cache , DB cn pooled out n wrapped in
															// the Session
		Session session2 = getFactory().getCurrentSession();
		Session session3 = getFactory().getCurrentSession();
		System.out.println(session == session2);// t
		System.out.println(session == session3);// t
		System.out.println("Session is open " + session.isOpen() + " is connected " + session.isConnected());// t t
		// 2. Begin a Transaction
		Transaction tx = session.beginTransaction();

		try {
			// 3. Session API for inserting a record.
			session.persist(newUser);// newUser - PERSISTENT
			tx.commit();// DML - insert
			System.out.println("Sesison is open " + session.isOpen() + " is connected " + session.isConnected());// f f
			// newUser - DETACHED (from L1 cache)
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();// discard tx
			}
			throw e;
		}
		return "User registered successfully , with ID=" + newUser.getId();
	}

	@Override
	public User getUserDetailsById(Long userId) {
		User user = null;// user - DOES NOT Exist
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			user = session.find(User.class, userId);
			// in case of valid id , user - PERSISTENT(exists in L1 cache as well as in DB)
			// in case of invalid id , user - null
			user = session.find(User.class, userId);
			user = session.find(User.class, userId);
			tx.commit();// session.flush() -> no DML -> session.close() -> L1 cache is destroyed & db cn
						// rets to the DBCP
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		// in case of valid id, user - DETACHED
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;// what to add here ?
		String jpql = "select u from User u";
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class) // Query<User>
					.getResultList();
			// users - List of PERSISTENT entities

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		// users - List of DETACHED entities
		return users;
	}

	@Override
	public List<User> getSelectedUsers(UserRole role1, LocalDate date1) {
		List<User> users = null;
		String jpql = "select u from User u where u.role=:rl and u.dob > :dt";
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("rl", role1).setParameter("dt", date1)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return users;
	}

	@Override
	public List<String> getSelectedUsersLastName(UserRole role1) {
		List<String> lastNames = null;
		String jpql = "select u.lastName from User u where u.role=:rl";
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			lastNames = session.createQuery(jpql, String.class).setParameter("rl", role1).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return lastNames;
	}

	@Override
	public List<User> getSelectedDetailsByRole(UserRole role1) {
		List<User> users = null;
		String jpql = "select new com.healthcare.entities.User(u.firstName,u.lastName,u.dob) from User u where u.role=:role";
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("role", role1).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return users;
	}

	@Override
	public String changePassword(String email1, String oldPassword1, String newPass) {
		String mesg = "Changing password failed ......";
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		User user = null;
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			user = session.createQuery(jpql, User.class).setParameter("em", email1).setParameter("pass", oldPassword1)
					.getSingleResult();
			// user - PERSISTENT (exists in DB n L1 cache)
			user.setPassword(newPass);
			// session.evict(user);//user - DETACHED
			/*
			 * Any time you modify the state of persistent entity - Hibernate performs auto
			 * dirty checking @commit - state updated => DML : update
			 */
			tx.commit();// session.flush -> DML -> session.close
			mesg = "Password changed !";

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		// user : DETACHED (not in L1 cache BUT exists in DB)
		user.setPassword("1111111111111111111");
		return mesg;
	}

	@Override
	public String applyDiscount(LocalDate localDate, UserRole role, int discountAmount) {
		String mesg = "Bulk updation failed !!!!!!!!";
		String jpql = "update User u set u.regAmount=u.regAmount-:discount where u.role=:rl and u.dob < :dt";
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			int rows = session.createMutationQuery(jpql).setParameter("discount", discountAmount)
					.setParameter("rl", role).setParameter("dt", localDate).executeUpdate();

			tx.commit();
			mesg = "Applied discount to " + rows + " users";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return mesg;
	}

	@Override
	public String deleteUserDetailsById(Long userId) {
		String mesg = "User deletion failed !!!!!!!";
		User user = null;
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			user = session.find(User.class, userId);
			if (user != null) {
				// user - PERSISTENT
				session.remove(user); // user - REMOVED
				mesg = "User details deleted ...";
			}
			tx.commit();// delete , close - removed from L1 cache
			// user - TRANSIENT
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return mesg;
	}// user - Marked for GC -> does not exist

	@Override
	public String saveImage(Long userId, String filePath) throws IOException {
		StringBuilder mesg = new StringBuilder("Saving image -  ");
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			// 3. Get user from its id
			User user = session.find(User.class, userId);
			if (user != null) {
				// => user : PERSISTENT (valid user id)
				// 4. Create & validate File instance
				File file = new File(filePath);
				if (file.isFile() && file.canRead()) {
					// => file valid
					byte[] imgData = FileUtils.readFileToByteArray(file);
					// 5. simple set image
					user.setImage(imgData);// modifying the state of persistent entity in heap
					mesg.append("Image will be saved in db ....@ commit");
				} else {
					mesg.append("Invalid Image file....");
				}
			} else {
				mesg.append("invalid user id !");
			}
			tx.commit();// session.flush -> dirty chking -> updated ->DML - update -> session.close
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return mesg.toString();
	}

	@Override
	public String restoreImage(String email1, String path) throws IOException {
		StringBuilder mesg = new StringBuilder("Restoring image -  ");
		String jpql = "select u from User u where u.email=:em";
		// 1. get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			// 3. Get User by email
			User user = session.createQuery(jpql, User.class).setParameter("em", email1).getSingleResult();
			// 4. => user : persistent
			byte[] imageData = user.getImage();
			if (imageData != null) {
				// 5. => img exists
				FileUtils.writeByteArrayToFile(new File(path), imageData);
				mesg.append(" successful !");
			} else {
				mesg.append(" Image doesn't exist!!!!!");
			}
			tx.commit();//no DMLs
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return mesg.toString();
	}

}
