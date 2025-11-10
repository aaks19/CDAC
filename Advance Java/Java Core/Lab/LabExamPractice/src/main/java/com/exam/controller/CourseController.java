package com.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.CourseRegisterDto;
import com.exam.dto.CourseUpdateDto;
import com.exam.service.CourseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
	private final CourseService courseService;
	
	@PostMapping("/addCourse")
	public ResponseEntity<?> addnewCourse(@RequestBody CourseRegisterDto dto){
		System.out.println("in student resister = "+dto);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(courseService.newCourse(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already exists...");
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCourse(@RequestBody CourseUpdateDto updateCourseDto){
		System.out.println("in update course - "+updateCourseDto);
		try {
			return ResponseEntity.ok(courseService.updateCourseById(updateCourseDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Id not found");
		}
	}
	
	@PostMapping("/listAllCourses/{category}")
	public ResponseEntity<?> listAllCourse(@PathVariable String category){
		System.out.println("in list all course - "+category);
		try {
			return ResponseEntity.ok(courseService.listAllCourseByCourseCategory(category));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course not found");
		}		
	}
}
