package com.exam.dto;

import com.exam.entities.Course;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentRegisterDto {

	@NotBlank
	private String studentName;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
	private double marks;
	
	private Long courseId;

}
