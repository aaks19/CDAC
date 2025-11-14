package com.exam.dto;

import com.exam.entity.Department;

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
	@NotNull
	private Department department;
	@NotNull
	private String designation;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0.0")
	private double salary;
}
