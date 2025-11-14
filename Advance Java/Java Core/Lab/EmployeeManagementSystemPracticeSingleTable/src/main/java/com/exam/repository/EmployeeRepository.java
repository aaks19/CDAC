package com.exam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.Department;
import com.exam.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	boolean existsByEmail(String email);

	List<Employee> findByDepartment(Department dept);

	List<Employee> findBySalaryGreaterThanEqual(Double minSalary);

	List<Employee> findByJoiningDateBetween(LocalDate startDate, LocalDate endDate);

}
