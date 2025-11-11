package com.exam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.dao.CourseDao;
import com.exam.dao.StudentDao;
import com.exam.dto.StudentDTO;
import com.exam.entity.Course;
import com.exam.entity.Student;
import com.exam.exception.ResourseAlreadyExistException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentDao studentDao;
	private final CourseDao courseDao;
	private final ModelMapper mapper;

	@Override
	public String addStudent(StudentDTO dto) {

		if (studentDao.existsByEmail(dto.getEmail())) {
			throw new ResourseAlreadyExistException("Student already exists");
		}

		Course course = courseDao.findById(dto.getCourseId())
				.orElseThrow(() -> new RuntimeException("Course not found"));

		Student newStudent = mapper.map(dto, Student.class);
		newStudent.setCourse(course);

		studentDao.save(newStudent);
		System.out.println("Student added");

		return "Student added";
	}

	@Override
	public String deleteStudentById(Long studentId) {
		if(studentDao.existsById(studentId)) {
			studentDao.deleteById(studentId);
		}
		return "Student deleted";
	}

//	@Override
//	public List<StudentDTO> fetchStudentByCourseName(String courseName) {
//		List<Student> byCourseName = studentDao.findByCourse_Name(courseName);
//
//		return byCourseName.stream().map(s -> mapper.map(s, StudentDTO.class)).toList();
//	}

}
