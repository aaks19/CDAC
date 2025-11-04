package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ems.service.DepartmentService;


@Controller
@RequestMapping("/department")
public class DepartmentController {
	//depcy - service layer i/f
	@Autowired //by type 
	private DepartmentService departmentService;
	
	public DepartmentController() {
		System.out.println("in ctor of "+getClass());
	}
	
	//http://host:port/ctx_path/department/list , method=GET
	@GetMapping("/list")
	public ModelAndView listAllDepartments() {
		System.out.println("in list all depts");
		return new ModelAndView("dept/list",
				"department_list", departmentService.getAllDepartments());
		//AVN - /WEB-INF/views/dept/list.jsp
	}

}
