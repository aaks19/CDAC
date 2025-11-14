package com.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.customException.ResourseAlreadyExistException;
import com.exam.dto.DepartmentDto;
import com.exam.entity.Department;
import com.exam.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeServiceImpl;

	private final DepartmentRepository deptRepo;
	private final ModelMapper mapper;

    
	
	@Override
	public String addNewDepartment(DepartmentDto deptDto) {
		if(deptRepo.existsByDeptName(deptDto.getDeptName())) {
			throw new ResourseAlreadyExistException("Department Already Exist");
		}
		Department department = mapper.map(deptDto, Department.class);
		Department deptSave = deptRepo.save(department);
		return "Department Added "+ deptSave;
	}

	@Override
	public String updateDepartment(DepartmentDto deptdto, Long deptId) {
		Department dept = deptRepo.findById(deptId).orElseThrow(()->new ResourseAlreadyExistException("dept not exist"));
		dept.setDeptName(deptdto.getDeptName());
		dept.setLocation(deptdto.getLocation());
		dept.setHead(deptdto.getHead());
		
		Department deptSave = deptRepo.save(dept);
//		if(deptRepo.existsById(deptId)) {
//			Department dept = mapper.map(deptId, Department.class);
//			dept.setDeptName(deptdto.getDeptName());
//			dept.setLocation(deptdto.getLocation());
//			dept.setHead(deptdto.getHead());
//		}
		return "department updated "+deptSave;
	}

}
