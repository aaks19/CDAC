package com.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	boolean existsByEmail(String email);

}
