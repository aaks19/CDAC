package com.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.DepartmentDto;
import com.exam.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
	private final DepartmentService departmentService;
	
	@PostMapping("/addDepartment")
	public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto deptDto){
		System.out.println("in department add controller");
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addNewDepartment(deptDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department already exist");
		}
	}
	
	@PostMapping("/updateDepartment/{deptId}")
	public ResponseEntity<?> updateDepartmentDetails(@RequestBody DepartmentDto deptdto, @PathVariable Long deptId){
		System.out.println("in department add controller");
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.updateDepartment(deptdto,deptId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department already exist");
		}
	}
}
