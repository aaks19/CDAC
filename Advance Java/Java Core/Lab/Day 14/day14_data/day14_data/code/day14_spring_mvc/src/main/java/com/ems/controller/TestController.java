package com.ems.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //req handling spring bean - mandatory
@RequestMapping("/test") //optional - BUT reco !

public class TestController {
	public TestController() {
		System.out.println("in ctor " + getClass());
	}
	/*
	 * URL - http://host:port/ctx_path/test/test1
	 * Key - /test/test1
	 * Value - TestController.testMe1
	 */
	@GetMapping("/test1") //@GetMapping = @RequestMapping(method=GET)
	//It can service only HTTP GET request
	public ModelAndView testMe1() {
		System.out.println("in test me 1");
		/*
		 * ModelAndView(String viewName,String attrName,Object value)
		 */
		return new ModelAndView("display/display1", "server_ts", LocalDateTime.now());//AVN - /WEB-INF/views/display/disaply1.jsp
	}
	/*
	 * URL - http://host:port/ctx_path/test/test2
	 * Key - /test/test2
	 * Value - TestController.testMe2
	 */

}
