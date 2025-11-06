package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Status;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	@Query("""
			select new com.healthcare.dto.AppointmentDTO(a.id,a.appointmentDateTime,a.myDoctor.userDetails.firstName,a.myDoctor.userDetails.lastName) 
			from Appointment a where a.myPatient.id=:pid and a.status=:sts order by a.appointmentDateTime asc
			""")
	List<AppointmentDTO> getPatientUpcomingAppointmentsByPatientId(@Param("pid") Long patientId,
			@Param("sts") Status status);
}
