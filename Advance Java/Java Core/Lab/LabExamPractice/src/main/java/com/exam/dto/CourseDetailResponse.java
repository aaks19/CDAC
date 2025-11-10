package com.exam.dto;

import java.time.LocalDate;

import com.exam.entities.Category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseDetailResponse {
	private Long id;
    private String courseName;
    private Category category;
    private LocalDate startDate;
    private LocalDate endDate;
    private double fees;
    private double marksToPass;
}
