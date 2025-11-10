package com.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.StudentRegisterDto;
import com.exam.service.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
	private final StudentService studentService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerStudent(@RequestBody StudentRegisterDto dto){
		System.out.println("in student resister = "+dto);
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(studentService.registerNewStudent(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student not registered");
		
		}
	}
	
	@GetMapping("/list_students/{courseName}")
	public ResponseEntity<?> listStudent(@PathVariable String courseName){
		System.out.println("in list student = "+courseName);
		
		try {
			return ResponseEntity.ok(studentService.listAllStudentByCourseName(courseName));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student not found for course "+courseName );
		}
	}
}
