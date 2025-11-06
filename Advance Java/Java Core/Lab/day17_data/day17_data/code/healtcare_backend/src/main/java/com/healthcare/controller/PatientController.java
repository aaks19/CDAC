package com.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.ApiResponse;
import com.healthcare.dto.BookAppointmentDTO;
import com.healthcare.service.AppointmentService;
import com.healthcare.service.BookAppointmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

	// dependency
	private final AppointmentService appointmentService;
	private final BookAppointmentService bookAppointmentService;

	@GetMapping("/{patientId}/appointments/upcoming")
	public ResponseEntity<?> listUpcomingAppointments(@PathVariable Long patientId) {
		System.out.println("in patient list appointments " + patientId);
		// invoker service layer method
		try {
			return ResponseEntity.ok(appointmentService.listUpcomingPatientAppointments(patientId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND) // SC 404
					.body(new ApiResponse(e.getMessage(), "failed"));
		}
	}

	// Create a new appointment
	@PostMapping("/{pid}/appointments")
	public ResponseEntity<?> addNewAppointment(@PathVariable Long pid, @RequestBody BookAppointmentDTO dto) {
		System.out.println("in patient book appointment " + pid + " " + dto);
		try {
			return ResponseEntity.ok(bookAppointmentService.bookNewAppointment(pid,dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage(), "Appointment of that doctor is already exist"));
		}

	}

}
