package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Employee;

public interface EmployeeService {

	Employee fetchData(int employeeId);

	void deleteEmployee(int employeeId);

	List<Employee> viewEmployee();
	
	void createEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
}
