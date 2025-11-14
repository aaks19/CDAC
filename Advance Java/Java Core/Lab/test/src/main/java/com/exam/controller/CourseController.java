package com.exam.controller;

import java.util.Locale.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.CourseDTO;
import com.exam.dto.CourseUpdateDetailsDTO;
import com.exam.service.CourseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/course") // mapping
@AllArgsConstructor
public class CourseController {

	// auto wired default
	private final CourseService courseService;

	// 1) add courses

	// post = insert
	@PostMapping("/add_course")
	public ResponseEntity<?> addCourse(@RequestBody CourseDTO dto) // ? -> anyThing dto, dao
	{
		try {

			return ResponseEntity.ok(courseService.addCourse(dto));

		} catch (RuntimeException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("course already exists");// 404 pageNot found
		}

	}

	// 2 Update Course Details

	@PutMapping("/update_course")
	public ResponseEntity<?> updateCourse(@RequestBody CourseUpdateDetailsDTO dto) {
		try {
			return ResponseEntity.ok(courseService.updateCourse(dto));

		} catch (RuntimeException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("course already exists");// 404 pageNot found
		}
	}

	// 3 fetch all details

	@GetMapping("/fetchCourseDetails/{courseCategory}")
	public ResponseEntity<?> fetchCourseDetails(@PathVariable String courseCategory) {
		try {
			return ResponseEntity.ok(courseService.fetchCourseDetails(courseCategory));

		} catch (RuntimeException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("course already exists");// 404 pageNot found
		}
	}

	

	@DeleteMapping("/deleteCourse/{courseId}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
		try {
			return ResponseEntity.ok(courseService.deleteCourseByCourseId(courseId));

		} catch (RuntimeException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("course not exists");// 404 pageNot found
		}
	}

}
