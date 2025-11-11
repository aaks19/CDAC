package com.exam.dto;

import java.time.LocalDate;

import com.exam.entity.Category;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CourseDTO {

	
	private String name;
	private Category category;
	private LocalDate startDate;
	private LocalDate endDate;
	private double fees;
	private double marks;
	
	
	
}
