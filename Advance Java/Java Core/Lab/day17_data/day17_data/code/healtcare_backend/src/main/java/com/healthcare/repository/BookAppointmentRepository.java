package com.healthcare.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entities.Appointment;

public interface BookAppointmentRepository extends JpaRepository<Appointment, Long> {
	
	Optional<Appointment> findByMyDoctorIdAndAppointmentDateTime(Long doctorId, LocalDateTime ts);
	
}
