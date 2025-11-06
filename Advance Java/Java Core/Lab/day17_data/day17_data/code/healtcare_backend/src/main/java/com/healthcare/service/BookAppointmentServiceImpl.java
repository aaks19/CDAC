package com.healthcare.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dto.BookAppointmentDTO;
import com.healthcare.dto.BookAppointmentResponse;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.Patient;
import com.healthcare.entities.Status;
import com.healthcare.repository.BookAppointmentRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BookAppointmentServiceImpl implements BookAppointmentService {

	private final BookAppointmentRepository bookAppointmentRepository;

	@Override
	public Appointment bookNewAppointment(Long patientId, BookAppointmentDTO dto) {
//		Appointment value = bookAppointmentRepository.findByMyDoctorIdAndAppointmentDateTime(dto.getDoctorId(), dto.getAppointmentDateTime())
//				.orElseThrow(() -> new ResourceNotFoundException("doctor not available"));
////		value.setMyDoctor(value.getMyDoctor());
////		value.setMyPatient(value.getMyPatient());
////		value.setAppointmentDateTime(dto.getAppointmentDateTime());
//		value.setMyDoctor(value.getMyDoctor());
//		value.setMyPatient(value.getMyPatient());
//		value.setAppointmentDateTime(value.getAppointmentDateTime());
//		bookAppointmentRepository.save(value);
//		return null;
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		
		Optional<?> appoint = bookAppointmentRepository
				.findByMyDoctorIdAndAppointmentDateTime(dto.getDoctorId(), dto.getAppointmentDateTime());

		if(appoint.isPresent()) {
			throw new ResourceNotFoundException("Appointment already exist");
		}
		Appointment appointment = new Appointment();
		appointment.setMyDoctor(doctor);
		doctor.setId(dto.getDoctorId());
		appointment.setMyDoctor(doctor);
		patient.setId(patientId);
		appointment.setMyPatient(patient);
		
		appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
		appointment.setStatus(Status.SCHEDULED);
		
		Appointment save = bookAppointmentRepository.save(appointment);
		
		return save;
	}

}
