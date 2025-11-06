package com.healthcare.services;

import java.util.List;

import com.healthcare.entities.Patient;

public interface PatientServices {

	List<Patient> listAllPatients();

	String addPatient(Patient newPatient);
}
