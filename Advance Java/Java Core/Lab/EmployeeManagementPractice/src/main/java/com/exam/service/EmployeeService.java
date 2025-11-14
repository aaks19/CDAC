package com.exam.service;

import java.util.List;

import com.exam.dto.EmployeeDto;

public interface EmployeeService {

	String addNewEmployee(EmployeeDto empdto);

	List<EmployeeDto> fetchAllEmployeeByDepartmentName(String deptName);
	
}
