package com.exam.service;

import java.util.List;

import com.exam.dto.StudentDTO;

public interface StudentService {

	public String addStudent(StudentDTO dto);

	public String deleteStudentById(Long studentId);

//	public List<StudentDTO> fetchStudentByCourseName(String courseName);
}
