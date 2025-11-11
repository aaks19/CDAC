package com.exam.service;

import java.util.List;

import com.exam.dto.CourseDTO;
import com.exam.dto.CourseFetchDetailsDTO;
import com.exam.dto.CourseUpdateDetailsDTO;
import com.exam.dto.StudentDTO;

public interface CourseService {

	public String addCourse(CourseDTO dto) ;
	
	public String updateCourse(CourseUpdateDetailsDTO dto);
	
	public List<CourseFetchDetailsDTO> fetchCourseDetails(String category);

	List<StudentDTO> fetchStudentByCourseName(String courseName);

	public String deleteCourseByCourseId(Long courseId);
	
	
}
