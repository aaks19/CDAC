package com.healthcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.customException.ResourseAlreadyExists;
import com.healthcare.dao.PatientDao;
import com.healthcare.entities.Patient;
import com.healthcare.entities.UserRole;

@Service
@Transactional
public class PatientServicesImpl implements PatientServices{

	@Autowired
	private PatientDao patientDao;
	
	@Override
	public List<Patient> listAllPatients() {
		return patientDao.findAll();
	}

	@Override
	public String addPatient(Patient newPatient) {
		// check if patient exists or not
		if(patientDao.existsByName(newPatient.getUserDetails().getFirstName())) {
			throw new ResourseAlreadyExists("Restaurant with the same name already exists");
		}
		newPatient.getUserDetails().setRole(UserRole.ROLE_PATIENT);
		Patient persistentPatient = patientDao.save(newPatient);
		
		return "New Patient added with patient id = "+persistentPatient.getId();
	}
	
}
