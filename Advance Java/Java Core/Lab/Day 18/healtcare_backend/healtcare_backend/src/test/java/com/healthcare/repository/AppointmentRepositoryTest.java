package com.healthcare.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entities.Status;

@DataJpaTest // to declare test case (Class) for DAO layer Testing - Scan repository + entities
@AutoConfigureTestDatabase(replace = Replace.NONE) // continues to use main DB - mysql
class AppointmentRepositoryTest {
	
	@Autowired //field leve D.I.
	private AppointmentRepository appointmentRespository;

	@Test
	void testGetPatientUpcomingAppointmentsByUserId() {
		List<AppointmentDTO> list = appointmentRespository.getPatientUpcomingAppointmentsByUserId(3l, Status.SCHEDULED);
		assertEquals(2, list.size());
		assertEquals(1l, list.get(0).getAppointmentId());
	}

}
