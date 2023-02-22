package com.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.model.Employee;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
@Controller
@RequestMapping("/employee")
public class EmployeeController extends HttpServlet {
	
	@Autowired
	EmployeeService employeeserviceimpl; 
	
	
	@RequestMapping("/view")
	public String viewEmployee(Model model) {
		model.addAttribute("employeeList",employeeserviceimpl.viewEmployee());
		return "ViewEmployee";
	}
	
	@RequestMapping("/create")
	public String createPage(Model model) {
		model.addAttribute("todayDate", LocalDate.now().toString());
		model.addAttribute("employee",new Employee());
		return "EmployeeDataEntry";
	}
	
	@RequestMapping("/update{employeeId}")
	public String updatePage(Model model,@PathVariable int employeeId) {
		model.addAttribute("todayDate", LocalDate.now().toString());
		model.addAttribute("employee",employeeserviceimpl.fetchData(employeeId));
		return "EmployeeDataEntry";
	}
	
	@RequestMapping("/delete{employeeId}")
	public String Employee(@PathVariable int employeeId) {
		employeeserviceimpl.deleteEmployee(employeeId);
		return "redirect:view";
	}
	
	@RequestMapping(value="/submit", method= RequestMethod.POST)
	public String createEmployee(@ModelAttribute("employee") Employee employee) {
		if(employee.getEmployeeId()==0) {
			employeeserviceimpl.createEmployee(employee);
		}else {
			employeeserviceimpl.updateEmployee(employee);
		}
		return "redirect:view";
	}
}
