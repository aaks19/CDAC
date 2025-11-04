package com.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ems.Day15MvcBootApplication;
import com.ems.custom_exceptions.ResourceNotFoundException;
import com.ems.dao.EmployeeDao;
import com.ems.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final Day15MvcBootApplication day15MvcBootApplication;
	// depcy
	@Autowired
	private EmployeeDao employeeDao;

	EmployeeServiceImpl(Day15MvcBootApplication day15MvcBootApplication) {
		this.day15MvcBootApplication = day15MvcBootApplication;
	}

	@Override
	public List<Employee> getEmpsByDeptId(Long deptId) {
		// TODO Auto-generated method stub
		return employeeDao.findByMyDepartmentId(deptId);
	}

	@Override
	public String deleteEmpDetails(Long empId) {
		String mesg = "Deleting emp details failed !!!!!!";
		if (employeeDao.existsById(empId)) {
			employeeDao.deleteById(empId);
			mesg = "Deleted emp details with ID=" + empId;
		}
		return mesg;
	}

	@Override
	public Employee getEmpDetailsById(Long empId) {
		Optional<Employee> optionalEmp = employeeDao.findById(empId);
		return optionalEmp.orElseThrow(() -> new ResourceNotFoundException("emp id invalid!!!!"));
	}

	@Override
	public String updateEmpDetails(Long id, Employee updatedEmp) {
		// validate emp id
		Employee existingEmp = getEmpDetailsById(id);
		// => existingEmp : PERSISTENT
		// invoke setters -modofying the state of persistent entity
		existingEmp.setFirstName(updatedEmp.getFirstName());
		existingEmp.setLastName(updatedEmp.getLastName());
		existingEmp.setEmail(updatedEmp.getEmail());
		existingEmp.setPassword(updatedEmp.getPassword());
		existingEmp.setSalary(updatedEmp.getSalary());
		existingEmp.setType(updatedEmp.getType());
		existingEmp.setJoinDate(updatedEmp.getJoinDate());
		/*
		 * DIY Instead of calling setters explicitly 
		 * BeanUtils.copyProperties
		 * static void copyProperties(Object source, Object target, String...
		 * ignoreProperties) throws BeansException
		 */

		return "Updated emp details with ID" + id;
	}// exc or no exc -> no exc -> tx.commit() -> DML - update

}
