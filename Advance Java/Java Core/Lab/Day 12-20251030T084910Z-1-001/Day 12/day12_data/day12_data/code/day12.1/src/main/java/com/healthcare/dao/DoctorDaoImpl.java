package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.entities.Doctor;

public class DoctorDaoImpl implements DoctorDao {

	@Override
	public String registerDoctor(Doctor doctor) {
		String mesg="Registering Doctor failed....";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			//session.persist(doctor.getUserDetails()); //if we comment this then we have to add cascade in the OneToOne entity of Doctor class entity.
			session.persist(doctor);
			tx.commit();
			mesg = "Registeration success with id - "+doctor.getId();
			
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exception to the caller
			throw e;
		}

		return mesg;
	}

	@Override
	public Doctor getDoctorDetails(Long userId) {
		Doctor doctor=null;
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
			// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exception to the caller
			throw e;
		}
		return doctor;
	}

}
