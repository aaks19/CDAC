package com.exam.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exam.controller.EmployeeController;
import com.exam.custom_exception.ResourceNotFoundException;
import com.exam.custom_exception.ResourseAlreadyExistException;
import com.exam.dto.EmployeeDto;
import com.exam.dto.UpdateEmployee;
import com.exam.dto.UpdateSalaryDto;
import com.exam.entities.Department;
import com.exam.entities.Employee;
import com.exam.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository empRepo;
	private final ModelMapper mapper;

    
	@Override
	public String addNewEmployee(EmployeeDto empDto) {
		if(empRepo.existsByEmail(empDto.getEmail())) {
			throw new ResourseAlreadyExistException("Email already exist...");
		}
		

		if(empDto.getJoiningDate().isBefore(LocalDate.now())) {
			
		Employee emp = mapper.map(empDto, Employee.class);
		Employee empAdded = empRepo.save(emp);
		return "Employee Added : "+empAdded.getId();
		}
		return null;
	}
	
	@Override
	public String updateEmployeeDetails(Long empId, UpdateEmployee updateDto) {
		Employee employee = empRepo.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not found..."));
		
		employee.setDepartment(updateDto.getDepartment());
		employee.setDesignation(updateDto.getDesignation());
		employee.setSalary(updateDto.getSalary());
		
		Employee empUpdated = empRepo.save(employee);
		return "Employee updated :"+empUpdated.getId();
	}

	@Override
	public List<EmployeeDto> FetchEmployeeDetailsByDeptName(String deptName) {
		Department dept = Department.valueOf(deptName.toUpperCase());
		List<Employee> empList = empRepo.findByDepartment(dept);
		if(empList.isEmpty()) {
			throw new ResourceNotFoundException("Employee details not found.....");
		}
		List<EmployeeDto> emp = empList.stream().map(e->mapper.map(e, EmployeeDto.class)).toList();
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees(Double minSalary) {
if(minSalary != null) {
	return empRepo.findBySalaryGreaterThanEqual(minSalary);
}
		return empRepo.findAll();
	}

	@Override
	public String deleteEmployeeById(Long empId) {
		if(!empRepo.existsById(empId)) {
			throw new ResourceNotFoundException("Employee ID not found...");
		}
		empRepo.deleteById(empId);
		return "Employee deleted with id : "+empId;
		
	}

	@Override
	public List<Employee> getEmployeeBetweenTwoJoiningDate(LocalDate startDate, LocalDate endDate) {
		List<Employee> empDetail = empRepo.findByJoiningDateBetween(startDate,endDate);
		return empDetail;
	}

	@Override
	public String updateSalaryById(Long empId, UpdateSalaryDto salDto) {
		Employee emp_id = empRepo.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee id not found"));
		
		emp_id.setSalary(salDto.getSalary());
		
		Employee save = empRepo.save(emp_id);
		return "salary is updated "+save;
	}
	
	

}
