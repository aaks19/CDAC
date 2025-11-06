package com.healthcare.service;

import com.healthcare.dto.BookAppointmentDTO;
import com.healthcare.entities.Appointment;

public interface BookAppointmentService {
	Appointment bookNewAppointment(Long patientId,BookAppointmentDTO dto);
}
