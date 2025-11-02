package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.dao.DepartmentDao;
import com.ems.entities.Department;

@Service // mandatory class level annotation to declare Spring Bean containing Business Logic.
@Transactional // mandatory annotation for auto transaction management
public class DepartmentServiceImpl implements DepartmentService {
	// dependency - dao layer interface
	@Autowired //byType
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> getAllDepartments() {
		// Invokes DAO's method
		return departmentDao.getAllDepartments();
	}

}
