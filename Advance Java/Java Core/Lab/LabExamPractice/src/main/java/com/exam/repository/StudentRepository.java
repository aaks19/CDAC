package com.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	boolean existsByEmail(String email);

	
	List<Student> findByCourseCourseName(String courseName);

}
