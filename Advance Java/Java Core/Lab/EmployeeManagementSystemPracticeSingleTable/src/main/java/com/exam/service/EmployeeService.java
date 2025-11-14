package com.exam.service;

import java.time.LocalDate;
import java.util.List;

import com.exam.dto.EmployeeDto;
import com.exam.dto.UpdateEmployee;
import com.exam.dto.UpdateSalaryDto;
import com.exam.entities.Employee;

public interface EmployeeService {

	String addNewEmployee(EmployeeDto empDto);

	String updateEmployeeDetails(Long empId,UpdateEmployee updateDto);

	List<EmployeeDto> FetchEmployeeDetailsByDeptName(String deptName);

	List<Employee> getAllEmployees(Double minSalary);

	String deleteEmployeeById(Long empId);

	List<Employee> getEmployeeBetweenTwoJoiningDate(LocalDate startDate, LocalDate endDate);

	String updateSalaryById(Long empId,UpdateSalaryDto salDto);

}
