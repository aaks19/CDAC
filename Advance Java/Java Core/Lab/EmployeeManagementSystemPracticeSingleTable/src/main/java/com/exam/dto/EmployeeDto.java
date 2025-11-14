package com.exam.dto;

import java.time.LocalDate;

import com.exam.entities.Department;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	@NotBlank(message = "name cannot be null")
	private String empName;
	@NotBlank(message = "email cant be duplicate")
	private String email;
	@NotNull(message = "department cannot be null")
	private Department department;
	@NotNull(message = "designation cannot be null")
	private String designation;
	@NotNull(message = "salary cannot be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0.0")
	private double salary;
	@PastOrPresent(message = "joining date mest be of past")
	private LocalDate joiningDate;
}
