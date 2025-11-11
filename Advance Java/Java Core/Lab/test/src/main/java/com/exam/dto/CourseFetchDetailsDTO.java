package com.exam.dto;

import java.time.LocalDate;

import com.exam.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Fetch all courses by given Category
//- i/p category name

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseFetchDetailsDTO {
	
	private Long cid;

    private String name;

    private Category category;

    private LocalDate startDate;

    private LocalDate endDate;

    private double fees;

    private double marksToPass;

	
}
