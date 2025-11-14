package com.exam.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.customException.ResourseAlreadyExistException;
import com.exam.dto.EmployeeDto;
import com.exam.entity.Department;
import com.exam.entity.Employee;
import com.exam.repository.DepartmentRepository;
import com.exam.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository emprepo;
	private final DepartmentRepository deptrepo;
	private final ModelMapper mapper;

	@Override
	public String addNewEmployee(EmployeeDto empdto) {
		if(emprepo.existsByEmail(empdto.getEmail())) {
			throw new ResourseAlreadyExistException("Employee already exist");
		}
		
		Department dept = deptrepo.findById(empdto.getDepartmentId()).orElseThrow(()-> new ResourseAlreadyExistException("course not found"));
		Employee emp = mapper.map(empdto, Employee.class);
		emp.setDepartment(dept);
		Employee empSave = emprepo.save(emp);
		return "Employee Added Successfully "+empSave;
	}

	@Override
	public List<EmployeeDto> fetchAllEmployeeByDepartmentName(String deptName) {
		List<Employee> empDetails = emprepo.findByDepartmentDeptName(deptName);
		
		List<EmployeeDto> listOfEmps = empDetails.stream().map(e->mapper.map(e, EmployeeDto.class)).toList();
		return listOfEmps;
	}
	
	

}
