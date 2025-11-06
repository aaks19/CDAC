package com.healthcare.service;

import java.util.List;

import com.healthcare.dto.AppointmentDTO;

public interface AppointmentService {
	List<AppointmentDTO> listUpcomingPatientAppointments(Long patientId);
}
