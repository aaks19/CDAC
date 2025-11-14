package com.exam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long>
{

	boolean existsByEmail(String email);

//	@Query("select s from Student s inner join Course c on s.id = c.course_id where c.name =: cname ")
//	List<Student> getByCourseName(@Param("cname") String courseName);
	
	List<Student> findByCourseName(String courseName);

	
}
