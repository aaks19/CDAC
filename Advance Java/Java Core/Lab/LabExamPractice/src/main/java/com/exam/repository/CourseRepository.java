package com.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
