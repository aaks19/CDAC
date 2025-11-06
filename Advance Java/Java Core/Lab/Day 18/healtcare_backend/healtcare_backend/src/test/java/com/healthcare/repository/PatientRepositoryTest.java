package com.healthcare.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.healthcare.entities.DiagnosticTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PatientRepositoryTest {

	@Autowired
	private PatientRepository patientRepository;
	
	@Test
	void testGetAllTestForPatient() {
		Set<DiagnosticTest> tests = patientRepository.getAllTestForPatient(1l);
		assertEquals(4, tests.size());
	}

}
