package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/emps")
public class EmployeeController {
	//depcy
	@Autowired
	private EmployeeService employeeService;
	/*
	 * Add req handling method - to render list of emps from dept
	 * URL - http://host:port/ctx_path/emps/list ,method=POST
	 * payload - departmentId =....
	 */
	@RequestMapping("/list")
	public String listEmpsByDepartment(Model modelAttrMap, 
			@RequestParam(required=false) Long departmentId)
	// @RequestParam method argument annotation to bind incomming req param -> req handling method arguments.
	// Long departmentId = Long.parseLong(request.getParameter("departmentId"))
	{
		System.out.println("in list emps "+modelAttrMap+" "+departmentId);//{}
		modelAttrMap.addAttribute("emp_list", employeeService.getEmpsByDeptId(departmentId));
		return "emps/list";//AVN - /WEB-INF/views/emps/list.jsp
		/*
		 * Handler returns explicitly --> LVN --> DS
		 * SC sends implicitly - model map
		 * DS -> LVN -> V.R. -> AVN -> DS
		 * DS checks for model attribute -> present -> adds it under request scope
		 * forwards the client to the view layer.
		 */
	}
	
	// http://localhost:8080/ems/emps/delete?id=3 , method = GET , Query- ?id=3
	@GetMapping("/delete")
	public String deleteEmpByDeptId(Model modelMap, @RequestParam Long id, HttpSession session) {
		System.out.println("in delete "+modelMap+" "+id);
		
		// invoke service layer method
		session.setAttribute("message", employeeService.deleteById(id));
		return "redirect:/emps/list";
	}

}
