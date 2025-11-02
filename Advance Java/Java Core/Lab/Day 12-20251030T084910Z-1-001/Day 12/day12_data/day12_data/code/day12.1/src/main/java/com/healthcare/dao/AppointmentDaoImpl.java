package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.Patient;
import com.healthcare.entities.Status;

public class AppointmentDaoImpl implements AppointmentDao {

	@Override
	public String bookAppointment(Long doctorId, Long patientId, LocalDateTime ts) {
		String mesg = "Appointment un available , Please choose another Date/Time";
		Appointment appointment = null;

		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {

			if (isDoctorAvailable(doctorId, ts)) {
				Doctor doctor = session.find(Doctor.class, doctorId);
				Patient patient = session.find(Patient.class, patientId);
				appointment.setMyDoctor(doctor);
				appointment.setMyPatient(patient);
				appointment.setAppointmentDateTime(ts);

				session.persist(appointment);

				mesg = "Appointment Done...";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exception to the caller
			throw e;
		}
		return mesg;
	}

	@Override
	public String cancelAppointment(Long appointmentId, Long patientId) {
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
		return null;
	}

	private boolean isDoctorAvailable(Long docId, LocalDateTime ts) {
		String jpql = "select a from Appointment a where a.myDoctor.id =:did and appointmentDateTime > :tms";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			List<Appointment> doctorDetail = session.createQuery(jpql, Appointment.class)
					.setParameter("did", docId)
					.setParameter("tms", ts.plusMinutes(30))
					.getResultList();
			if (doctorDetail != null) {
				return true;
			}

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exception to the caller
			throw e;
		}
		return false;
	}

	@Override
	public List<AppointmentDTO> listUpcomingAppointmentsForPatient(Long patientId) {
		List<AppointmentDTO> appointmentList = null;
		String jpql = "select new com.healthcare.dto.AppointmentDTO(a.id,a.appointmentDateTime,a.myDoctor.userDetails.firstName,a.myDoctor.userDetails.lastName) from Appointment a where a.myPatient.id= :pid and a.status =:sts";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			appointmentList = session.createQuery(jpql, AppointmentDTO.class).setParameter("pid", patientId)
					.setParameter("sts", Status.SCHEDULED).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exception to the caller
			throw e;
		}
		return appointmentList;
	}

	@Override
	public List<AppointmentDTO> listUpcomingAppointmentsForDoctor(Long doctorId) {

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
		return null;

	}

}
