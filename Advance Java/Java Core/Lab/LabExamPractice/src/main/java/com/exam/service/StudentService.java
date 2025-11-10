package com.exam.service;

import java.util.List;

import com.exam.dto.StudentDetailResponse;
import com.exam.dto.StudentRegisterDto;
import com.exam.entities.Student;

public interface StudentService {
	String registerNewStudent(StudentRegisterDto studentDto);

	List<StudentDetailResponse> listAllStudentByCourseName(String courseName);
}
