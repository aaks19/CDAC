	package com.exam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.custom_exception.ResourceNotFoundException;
import com.exam.custom_exception.ResourseAlreadyExist;
import com.exam.dto.StudentDetailResponse;
import com.exam.dto.StudentRegisterDto;
import com.exam.entities.Course;
import com.exam.entities.Student;
import com.exam.repository.StudentRepository;
import com.exam.repository.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	private final ModelMapper mapper;
	
	@Override
	public String registerNewStudent(StudentRegisterDto studentDto) {
		if (studentRepository.existsByEmail(studentDto.getEmail())) {
		    throw new ResourseAlreadyExist("Student already exists with email: " + studentDto.getEmail());
		}
		
		 Course course = courseRepository.findById(studentDto.getCourseId())
		            .orElseThrow(() -> new ResourceNotFoundException("Invalid course ID"));

		    Student student = mapper.map(studentDto, Student.class);
		    student.setCourse(course);

		    Student saved = studentRepository.save(student);
		    return "New student added: " + saved.getStudentName();
	}

	@Override
	public List<StudentDetailResponse> listAllStudentByCourseName(String courseName) {
		List<Student> studentList = studentRepository.findByCourseCourseName(courseName);
		return studentList.stream().map(c->mapper.map(c, StudentDetailResponse.class)).toList();
	}

}
