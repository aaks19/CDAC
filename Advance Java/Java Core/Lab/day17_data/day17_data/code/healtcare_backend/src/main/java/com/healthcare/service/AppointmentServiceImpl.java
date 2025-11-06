package com.healthcare.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.entities.Status;
import com.healthcare.repository.AppointmentRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;

	public List<AppointmentDTO> listUpcomingPatientAppointments(Long patientId) {
		// by patient id
		return appointmentRepository.getPatientUpcomingAppointmentsByPatientId(patientId, Status.SCHEDULED);
	}

}
