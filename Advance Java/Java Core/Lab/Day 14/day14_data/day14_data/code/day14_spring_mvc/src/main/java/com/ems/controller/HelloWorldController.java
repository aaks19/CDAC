package com.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * singleton & eager , req handling spring bean
 */
@Controller
public class HelloWorldController {
	public HelloWorldController() {
		System.out.println("in ctor of "+getClass());
	}
	/*
	 * URL - http://host:port/ctx_path/
	 * HandlerMapping 
	 * Key - /
	 * Value - com.ems.controller.HelloWorldController.sayHello
	 */
	@RequestMapping("/")
	public String sayHello() {
		System.out.println("in say hello");
		return "index";//Logical View Name -> D.S -> V.R -> AVN -> D.S -> forward -> AVN : WEB-INF/views/index.jsp
	}

}
