package com.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entities.Patient;
import com.healthcare.services.PatientServices;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientServices patientServices;
	
	public PatientController() {
		System.out.println("in constructor of "+getClass());
	}
	
	
	//url : http://localhost:8080/patients , method - GET
	@GetMapping
	public ResponseEntity<?> listAllPatient(){
		System.out.println("list of patients");
		
		List<Patient> list = patientServices.listAllPatients();
		
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(list);
	}
	
	//add patient details
	/*
	 * url : http:/localhost:8080/patients
	 * method : POST
	 */
	@PostMapping
	public ResponseEntity<?> addNewPatient(@RequestBody Patient newPatient){
		System.out.println("in add new patient "+ newPatient);
		
		try {
			String message = patientServices.addPatient(newPatient);
			//success
			return ResponseEntity.status(HttpStatus.CREATED).body(message);
		}catch (RuntimeException e) {
			System.out.println("Error: "+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
