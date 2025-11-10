package com.exam.dto;

import java.time.LocalDate;

import com.exam.entities.Category;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseRegisterDto {
	@NotBlank
	private String courseName;
	@Enumerated(EnumType.STRING)
	private Category category;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	@NotNull
	private double fees;
	@NotNull
	private double marksToPass;
}
