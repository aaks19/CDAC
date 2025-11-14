package com.exam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.Application;
import com.exam.dao.CourseDao;
import com.exam.dao.StudentDao;
import com.exam.dto.CourseDTO;
import com.exam.dto.CourseFetchDetailsDTO;
import com.exam.dto.CourseUpdateDetailsDTO;
import com.exam.dto.StudentDTO;
import com.exam.entity.Category;
import com.exam.entity.Course;
import com.exam.entity.Student;
import com.exam.exception.ResourseAlreadyExistException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final StudentDao studentDao;

    private final Application application;

	// dao depy
	private final CourseDao courseDao;
	private final ModelMapper mapper;



	@Override
	public String addCourse(CourseDTO dto) {

		// Course newCourse = courseDao.findById(dto.getId()).orElseThrow(()->new
		// ResourseAlreadyExistException("course not "));
		if (courseDao.existsByName(dto.getName())) {
			throw new ResourseAlreadyExistException("course already exists");
		}

		Course course = mapper.map(dto, Course.class);

		Course courseSave = courseDao.save(course);

		return "succ course added.." + courseSave;

	}

	@Override
	public String updateCourse(CourseUpdateDetailsDTO dto) {
		
		Course course =courseDao.findById(dto.getId()).orElseThrow(()->new ResourseAlreadyExistException("not found"));
		
		course.setFees(dto.getFees());
		course.setEndDate(dto.getEndDate());
		course.setStartDate(dto.getStartDate());
		
		Course courseSave = courseDao.save(course);
		
		
		return "succ course updated...";
	}

	@Override
	public List<CourseFetchDetailsDTO> fetchCourseDetails(String category) {
		Category cat = Category.valueOf(category.toUpperCase());
		List<Course> byCategory = courseDao.findByCategory(cat);
		List<CourseFetchDetailsDTO> list = byCategory.stream().map(c->mapper.map(c, CourseFetchDetailsDTO.class)).toList();
		return list;
	}

	

	@Override
	public String deleteCourseByCourseId(Long courseId) {
		if(courseDao.existsById(courseId)) {
			courseDao.deleteById(courseId);
		}
		return "Course deleted";
	}

	
	
	

}
