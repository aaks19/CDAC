package com.exam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.custom_exception.ResourceNotFoundException;
import com.exam.dto.CourseDetailResponse;
import com.exam.dto.CourseRegisterDto;
import com.exam.dto.CourseUpdateDto;
import com.exam.entities.Category;
import com.exam.entities.Course;
import com.exam.repository.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final ModelMapper mapper;

	@Override
	public String newCourse(CourseRegisterDto dto) {
		if (courseRepository.existsByCourseName(dto.getCourseName())) {
			throw new ResourceNotFoundException("Course already exist..." + dto.getCourseName());
		}

		Course course = mapper.map(dto, Course.class);

		Course addedCourse = courseRepository.save(course);

		return "new course added with id - " + addedCourse.getId() + addedCourse.getCourseName();
	}

	@Override
	public String updateCourseById(CourseUpdateDto updateCourseDto) {

		Course updateCourse = courseRepository.findById(updateCourseDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("course not found"));
		
		updateCourse.setStartDate(updateCourseDto.getStartDate());
		updateCourse.setEndDate(updateCourseDto.getEndDate());
		updateCourse.setFees(updateCourseDto.getFees());
		
		Course saveCourse = courseRepository.save(updateCourse);
		
		return "Course updated -"+saveCourse;
	}

	@Override
	public List<CourseDetailResponse> listAllCourseByCourseCategory(String category) {
		Category ccategory = Category.valueOf(category.toUpperCase());
		List<Course> courseList = courseRepository.findByCategory(ccategory);
		return courseList.stream().map(c->mapper.map(c, CourseDetailResponse.class)).toList();
		
		
	}

}
