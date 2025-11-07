package com.healthcare.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	@Before("execution (* com.healthcare.controller.*.*(...))")
	public void startLogging(JoinPoint joinPoint)
	{
		System.out.println("in before advice....");
		//add logging message here.....
		log.debug("Logging every incomming request here - "+joinPoint);
	}

}
