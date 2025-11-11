package com.exam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Category;
import com.exam.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {

	boolean existsByName(String name);

	List<Course> findByCategory(Category cat);

	Course findByName(String courseName);

	
}
