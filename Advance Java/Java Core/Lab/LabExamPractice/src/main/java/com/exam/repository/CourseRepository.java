package com.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.entities.Category;
import com.exam.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	boolean existsByCourseName(String courseName);
	
//	@Query("select c from Course c where c.category=:cat")
	List<Course> findByCategory(Category category);

}
