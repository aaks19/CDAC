package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ems.dao.EmployeeDao;
import com.ems.entities.Employee;
import com.ems.entities.EmploymentType;
import com.ems.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/emps")
public class EmployeeController {

	// depcy
	@Autowired
	private EmployeeService employeeService;

	/*
	 * Add req handling method - to render list of emps from dept URL -
	 * http://host:port/ctx_path/emps/list ,method=POST payload - departmentId =....
	 */
	@RequestMapping("/list")
	public String listEmpsByDepartment(Model modelAttrMap, @RequestParam Long departmentId, HttpSession session) {
		System.out.println("in list emps " + modelAttrMap + " " + departmentId);// {}
		Long deptId = (Long) session.getAttribute("dept_id");
		if (deptId == null) {
			System.out.println("storing dept id under session");
			session.setAttribute("dept_id", departmentId);
		}
		modelAttrMap.addAttribute("emp_list", employeeService.getEmpsByDeptId(departmentId));
		return "emps/list";// AVN - /WEB-INF/views/emps/list.jsp
		/*
		 * Handler rets explicity -> LVN -> D.S SC sends implicitly - model map D.S ->
		 * LVN -> V.R -> AVN -> D.S D.S chks fro model attribute -> present -> adds it
		 * under request scope -> forwards the client to view layer
		 */
	}

	// URL - http://localhost:8080/ems/emps/delete?id=6 , method=GET
	// Query -?id=6
	@GetMapping("/delete")
	public String deleteEmpDetails(RedirectAttributes flashMap, @RequestParam Long id, HttpSession session) {
		System.out.println("in del emp dtls " + flashMap + " " + id);
		// invoke service layer method
		flashMap.addFlashAttribute("message", employeeService.deleteEmpDetails(id));
		return "redirect:/emps/list?departmentId=" + session.getAttribute("dept_id");
		/*
		 * SC / D.S - resp.sendRedirect(resp.encodeRedirectURL("emps/list"));
		 */

	}

	// URL - http://localhost:8080/ems/emps/update?id=... , method=GET
	@GetMapping("/update")
	public String showUpdateForm(@RequestParam Long id, Model map) {
		System.out.println("in show update form " + id + " " + map);
		// invoke service method -> emp -> model attribute
		map.addAttribute("emp_details", employeeService.getEmpDetailsById(id)).addAttribute("empTypes",
				EmploymentType.values());

		return "emps/update";// AVN - /WEB-INF/views/emps/update.jsp
	}

	// URL - http://localhost:8080/ems/emps/update?id=6 , method=POST
	@PostMapping("/update")
	public String processUpdateForm(@RequestParam Long id, @ModelAttribute(name = "emp_details") Employee updatedEmp,
			RedirectAttributes flashMap,HttpSession session) {
		System.out.println("in process update form " + id + " " + updatedEmp + " " + flashMap);
		//invoke service layer method
		flashMap.addFlashAttribute("message",employeeService.updateEmpDetails(id,updatedEmp));
		return "redirect:/emps/list?departmentId="+session.getAttribute("dept_id");
	}

}
