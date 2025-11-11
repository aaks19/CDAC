package com.exam.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//3.	Update Course Details 
//- i/p course id n new (Start Date, End Date, Fees)

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateDetailsDTO {

	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private double fees;
}
