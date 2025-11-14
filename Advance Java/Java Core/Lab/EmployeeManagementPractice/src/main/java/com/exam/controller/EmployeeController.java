package com.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.EmployeeDto;
import com.exam.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@PostMapping("/addemployee")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto empdto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addNewEmployee(empdto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee already exist");
		}
	}
	
	@PostMapping("/fetchEmployeeByDepartmentName/{deptName}")
	public ResponseEntity<?> fetchAllEmployeeByDepartmentName(@PathVariable String deptName){
		try {
			return ResponseEntity.ok().body(employeeService.fetchAllEmployeeByDepartmentName(deptName));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No employee found for dept name"+deptName);
		}
	}
	
}
