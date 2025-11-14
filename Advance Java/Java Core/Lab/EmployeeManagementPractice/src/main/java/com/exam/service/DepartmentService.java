package com.exam.service;

import com.exam.dto.DepartmentDto;

public interface DepartmentService {

	String addNewDepartment(DepartmentDto deptDto);

	String updateDepartment(DepartmentDto deptdto, Long deptId);

}
