package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ems.service.DepartmentService;

// http://host:port/ctx_path/department/list
@Controller
@RequestMapping("/department")
public class DepartmentController {

	private final TestController testController;
	// dependency - service layer interface
	@Autowired
	private DepartmentService departmentService;

	public DepartmentController(TestController testController) {
		System.out.println("in constr of " + getClass());
		this.testController = testController;
	}

	// http://host:port/ctx_path/department/list
	@RequestMapping("/list")
	public ModelAndView listAllDepartment() {
		System.out.println("in list all department");

		return new ModelAndView("dept/list", "department_list", departmentService.getAllDepartments());
		// AVN - WEB-INF/views/dept/list.jsp
	}
}
