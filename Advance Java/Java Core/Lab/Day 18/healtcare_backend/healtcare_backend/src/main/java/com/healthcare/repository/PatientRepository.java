package com.healthcare.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthcare.entities.DiagnosticTest;
import com.healthcare.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {
	//find all the diagnostic test prescribed to patients.
	
	@Query("select t from Patient p join p.diagnosticTests t where p.id =:pid")
	Set<DiagnosticTest> getAllTestForPatient(@Param("pid") Long patientId);
}
