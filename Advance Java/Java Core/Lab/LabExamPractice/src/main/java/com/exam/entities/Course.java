package com.exam.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="courses")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = "students")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 20, unique = true, nullable = false)
	private String courseName;
	@Enumerated(EnumType.STRING)
	private Category category;
	@Column(nullable = false)
	private LocalDate startDate;
	@Column(nullable = false)
	private LocalDate endDate;
	@Column(nullable = false)
	private double fees;
	@Column(nullable = false)
	private double marksToPass;
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Student> students = new ArrayList<>();

}
