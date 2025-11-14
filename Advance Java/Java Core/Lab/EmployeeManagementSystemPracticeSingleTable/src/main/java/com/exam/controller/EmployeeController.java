package com.exam.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.custom_exception.ResourceNotFoundException;
import com.exam.dto.EmployeeDto;
import com.exam.dto.UpdateEmployee;
import com.exam.dto.UpdateSalaryDto;
import com.exam.entities.Employee;
import com.exam.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;

//	@PostMapping("/addEmployee")
//	public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto empDto) {
//		try {
//			System.out.println("in dto" + empDto);
//			return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addNewEmployee(empDto));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee already exist...");
//		}
//	}
//
//	@PostMapping("/updateEmployee/{empId}")
//	public ResponseEntity<?> updateEmployee(@PathVariable Long empId, @RequestBody UpdateEmployee updateDto) {
//		try {
//			System.out.println("in dto" + updateDto);
//			return ResponseEntity.ok().body(employeeService.updateEmployeeDetails(empId, updateDto));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee already exist...");
//
//		}
//	}
//	
//	@GetMapping("/fetchAllEmployee/{deptName}")
//	public ResponseEntity<?> fetchAllEmployee(@PathVariable String deptName){
//		try {
//			System.out.println("in dto" + deptName);
//			return ResponseEntity.ok().body(employeeService.FetchEmployeeDetailsByDeptName(deptName));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee already exist...");
//
//		}
//	}
//	
//	@GetMapping("/fetchEmployee")
//	public ResponseEntity<?> fetchEmployee(@RequestParam(required = false) Double minSalary){
//		List<Employee> employees = employeeService.getAllEmployees(minSalary);
//		if (employees.isEmpty()) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found");
//	    }
//	    return ResponseEntity.ok(employees);
//	}
//	
//	@DeleteMapping("/deleteEmployee/{empId}")
//	public ResponseEntity<?> deleteEmployee(@PathVariable Long empId){
//		try {
//			return ResponseEntity.ok(employeeService.deleteEmployeeById(empId));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("employee Id not found...");
//		}
//	}
//	
//	@GetMapping("/fetchEmployeeBetweenJoiningDates")
//	public ResponseEntity<?> fetchEmployeeBetweenTwoDates(@RequestParam LocalDate startDate , @RequestParam LocalDate endDate){
//		try {
//			return ResponseEntity.ok(employeeService.getEmployeeBetweenTwoJoiningDate(startDate,endDate));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("employee Id not found...");
//		}
//	}
	
	
	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto empDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addNewEmployee(empDto));
	}
	
	@PostMapping("/updateEmployee/{empId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long empId, 
                                            @RequestBody UpdateEmployee updateDto) {
        return ResponseEntity.ok(employeeService.updateEmployeeDetails(empId, updateDto));
    }

    @GetMapping("/fetchAllEmployee/{deptName}")
    public ResponseEntity<?> fetchAllEmployee(@PathVariable String deptName) {
        return ResponseEntity.ok(employeeService.FetchEmployeeDetailsByDeptName(deptName));
    }

    @GetMapping("/fetchEmployee")
    public ResponseEntity<?> fetchEmployee(@RequestParam(required = false) Double minSalary) {
        List<Employee> employees = employeeService.getAllEmployees(minSalary);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long empId) {
        return ResponseEntity.ok(employeeService.deleteEmployeeById(empId));
    }

    @GetMapping("/fetchEmployeeBetweenJoiningDates")
    public ResponseEntity<?> fetchEmployeeBetweenTwoDates(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return ResponseEntity.ok(
            employeeService.getEmployeeBetweenTwoJoiningDate(startDate, endDate)
        );
    }
    
    @PutMapping("/updateSalary/{empId}")
    public ResponseEntity<?> updateSalary(@PathVariable Long empId, @RequestBody UpdateSalaryDto salDto){
    	return ResponseEntity.ok(employeeService.updateSalaryById(empId,salDto));
    }
    
}
