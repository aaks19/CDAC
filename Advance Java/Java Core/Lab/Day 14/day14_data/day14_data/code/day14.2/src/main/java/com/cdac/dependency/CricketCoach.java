package com.cdac.dependency;

import org.springframework.stereotype.Component;

//singleton & eager spring bean
@Component //to declare spring bean - derived | generated bean id - cricketCoach
public class CricketCoach implements Coach {

    public CricketCoach() {
    	System.out.println("In constructor - " + getClass());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
