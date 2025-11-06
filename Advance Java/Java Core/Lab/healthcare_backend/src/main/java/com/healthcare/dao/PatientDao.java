package com.healthcare.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entities.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {
	List<Patient> findAll();
	
	boolean existsByUserDetailsFirstName(String patientName);
}
