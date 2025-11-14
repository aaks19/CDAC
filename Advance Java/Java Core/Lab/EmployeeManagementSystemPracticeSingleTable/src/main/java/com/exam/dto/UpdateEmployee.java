package com.exam.dto;

import com.exam.entities.Department;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployee {
	@NotNull(message = "department cannot be null")
	private Department department;
	@NotNull(message = "designation cannot be null")
	private String designation;
	@NotNull(message = "salary cannot be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0.0")
	private double salary;
}
