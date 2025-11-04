package com.ems.service;

import java.util.List;

import com.ems.entities.Employee;

public interface EmployeeService {
//get all emps from dept by id
	List<Employee> getEmpsByDeptId(Long deptId);

	String deleteEmpDetails(Long empId);
	//add a method to get emp details
	Employee getEmpDetailsById(Long empId);

	String updateEmpDetails(Long id, Employee updatedEmp);
}
