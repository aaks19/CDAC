package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.dao.EmployeeDao;
import com.ems.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/emps")
public class EmployeeController {

    private final EmployeeDao employeeDao;
	//depcy
	@Autowired
	private EmployeeService employeeService;

    EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
	/*
	 * Add req handling method - to render list of emps from dept
	 * URL - http://host:port/ctx_path/emps/list ,method=POST
	 * payload - departmentId =....
	 */
	@RequestMapping("/list")
	public String listEmpsByDepartment(Model modelAttrMap, 
			@RequestParam(required = false) Long departmentId)
	//@RequestParam- method arg annotation to bind incoming rq param -> rq handling method arg.
	//Long departmentId=Long.parseLong(request.getParameter("departmentId))
	{
		System.out.println("in list emps "+modelAttrMap+" "+departmentId);//{}
		modelAttrMap.addAttribute("emp_list", employeeService.getEmpsByDeptId(departmentId));
		return "emps/list";//AVN - /WEB-INF/views/emps/list.jsp
		/*
		 * Handler rets explicity -> LVN -> D.S
		 * SC sends implicitly - model map 
		 * D.S -> LVN -> V.R -> AVN -> D.S
		 * D.S chks  fro model attribute -> present -> adds it under request scope
		 * -> forwards the client to view layer
		 */
	}
	//URL - http://localhost:8080/ems/emps/delete?id=6 , method=GET
	//Query -?id=6
	@GetMapping("/delete")
	public String deleteEmpDetails(Model modelMap,@RequestParam Long id,HttpSession session) {
		System.out.println("in del emp dtls "+modelMap+" "+id);
		//invoke service layer method
		session.setAttribute("message",employeeService.deleteEmpDetails(id));
		return "redirect:/emps/list";
		
	}


}
