package com.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	boolean existsByDeptName(String deptName);

}
