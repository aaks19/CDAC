package com.cdac.dependency;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//singleton & lazy
@Component("eng")
@Lazy(true)
/*
 * <bean id="eng" class="com.cdac.dependency.EnglishTeacher" lazy-init="true"/>
 */
public class EnglishTeacher implements Teacher {
	public EnglishTeacher() {
		System.out.println("In constructor - " + getClass());
	}

	@Override
	public void teach() {
		System.out.println("Practice English Grammar");
	}

}
