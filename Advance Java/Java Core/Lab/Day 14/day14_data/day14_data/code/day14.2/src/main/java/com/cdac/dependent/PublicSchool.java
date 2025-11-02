package com.cdac.dependent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cdac.dependency.Coach;
import com.cdac.dependency.Teacher;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
//singleton n eager , teacher - maths teach , coach - swimming coach
//Field level D.I , id-public_school
@Component("public_school")
public class PublicSchool implements School {
	@Autowired 
	@Qualifier("maths") //SC matches it by - name => SC checks is there any spring bean(@Component) with id=maths
	private Teacher subjectTeacher;
	@Autowired
	/* (required = false) */ @Qualifier("swimmingCoach")
	private Coach sportsCoach;

	public PublicSchool() {
		System.out.println("in ctor of " + getClass());
		
	}

	@Override
	public void manageAcademics() {
		System.out.println("Managing academics here -");
		subjectTeacher.teach();
	}

	@Override
	public void organizeSportsEvent() {
		System.out.println("Preapring for sports event -");
		System.out.println("Daily Workout " + sportsCoach.getDailyWorkout());

	}

	// custom init method
	@PostConstruct
	public void myInit() {
		System.out.println("in init of " + getClass());
	}

	// custom destroy method
	@PreDestroy
	public void myDestroy() {
		System.out.println("in destroy of " + getClass());
	}

	

}
