package com.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.StudentDTO;
import com.exam.service.StudentService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

	//no autowired- allAC
	private final StudentService studentService;
	
	
	@PostMapping("/add_student")
	public ResponseEntity<?> addStudent(@RequestBody StudentDTO dto) {
		
		try
		{
			return  ResponseEntity.ok(studentService.addStudent(dto));
		}
		catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body("Student already exists");// 404 
		}
		
		
		
	}
	
	//4 fetch all students details from course name
	@GetMapping("/fetchCourseDetails/{courseName}")
	public ResponseEntity<?> fetchStudentDetailsFromCourse(@PathVariable String courseName)
	{
		try {
			return ResponseEntity.ok(studentService.fetchStudentByCourseName(courseName));
		
		
	} catch (RuntimeException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("course already exists");// 404 pageNot found
	}
	}
	
	@DeleteMapping("/deleteStudent/{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long studentId){
		try {
			return ResponseEntity.ok(studentService.deleteStudentById(studentId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("student not exist");
		}
	}
	
}
