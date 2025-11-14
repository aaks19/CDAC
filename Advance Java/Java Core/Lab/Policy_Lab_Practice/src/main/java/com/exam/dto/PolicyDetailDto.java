package com.exam.dto;

import java.time.LocalDate;

import com.exam.entity.PolicyType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDetailDto {
	
	@NotNull(message = "Policy name cannot be empty")
	private String policyName;
	
	@NotNull(message = "Policy Type cannot be empty")
	private PolicyType policyType;
	
	@NotNull(message = "Premium amount cannot be empty")
	@DecimalMin(value = "0.0",inclusive = false,message = "amount cannot be 0.0" )
	private double accomodationAmmount;
	
	@NotNull(message = "Premium amount cannot be empty")
	@DecimalMin(value = "0.0",inclusive = false,message = "amount cannot be 0.0" )
	private double premiumAmount;
	
	@NotNull(message = "date cannot be null")
	private LocalDate startDate;
	
	@NotNull(message = "date cannot be null")
	private LocalDate endDate;
	
	@NotBlank(message = "holder name cannot be blank")
	private String policyHolderName;
}
