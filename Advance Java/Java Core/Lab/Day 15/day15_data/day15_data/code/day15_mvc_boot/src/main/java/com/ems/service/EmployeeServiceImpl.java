package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.dao.EmployeeDao;
import com.ems.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	//depcy
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> getEmpsByDeptId(Long deptId) {
		// TODO Auto-generated method stub
		return employeeDao.findByMyDepartmentId(deptId);
	}
	
	@Override
	public String deleteById(Long empId) {
		String msg = "Deletion failed...";
		if(employeeDao.existsById(empId)) {
			employeeDao.deleteById(empId);
			msg = "Deleted employee with id  = " + empId;
		}
		return msg;
	}

}
