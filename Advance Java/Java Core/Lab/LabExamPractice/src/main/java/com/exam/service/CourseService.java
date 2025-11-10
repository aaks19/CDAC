package com.exam.service;

import java.util.List;

import com.exam.dto.CourseDetailResponse;
import com.exam.dto.CourseRegisterDto;
import com.exam.dto.CourseUpdateDto;
import com.exam.entities.Category;
import com.exam.entities.Course;

public interface CourseService {
	String newCourse(CourseRegisterDto dto);

	String updateCourseById(CourseUpdateDto updateCourseDto);

	List<CourseDetailResponse> listAllCourseByCourseCategory(String category);

}
