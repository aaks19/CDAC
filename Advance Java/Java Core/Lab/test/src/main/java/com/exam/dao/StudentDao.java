package com.exam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long>
{

	boolean existsByEmail(String email);
	
//	List<Student> findByCourse_Name(String courseName);

	
}
