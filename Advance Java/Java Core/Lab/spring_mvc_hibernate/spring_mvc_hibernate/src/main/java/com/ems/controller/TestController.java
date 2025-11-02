package com.ems.controller;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	
	public TestController() {
		System.out.println("int the constructor of " + getClass());
	}
	
	/*
	 * Request http://host:port/ctx_path/
	 * Method - GET
	 */
	@RequestMapping("/")
	public ModelAndView testModelAndView() {
		System.out.println("in test model and view");
		
		/*
		 * ModelAndView(String viewName, String attrName, Object value
		 */
		
		return new ModelAndView("index","server_time",LocalTime.now());
		
		// AVN -> /WEB-INF/views/index.jsp
	}

}
