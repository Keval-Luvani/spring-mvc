package com.keval.SpringCrudApp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.keval.SpringCrudApp.dao.EmployeeDao;
import com.keval.SpringCrudApp.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDaoImpl;
	
	public Employee fetchData(int employeeId) {
		ResultSet resultSet = employeeDaoImpl.getEmployee(employeeId); 
		Employee employee = new Employee();
	    try {
			while(resultSet.next()) {
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setAge(resultSet.getInt(3));
				employee.setSalary(resultSet.getFloat(4));
				employee.setJoiningDate(resultSet.getString(5));
			    List<String> skillList = new ArrayList<String>();
				ResultSet skillResultSet = employeeDaoImpl.getSkills(resultSet.getInt(1));
				while(skillResultSet.next()){
					  skillList.add(skillResultSet.getString(1));
			    }
				employee.setSkillList(skillList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return employee;
	}

	public void deleteEmployee(int employeeId) {
		employeeDaoImpl.deleteEmployee(employeeId);
		return ; 
	}

	public List<Employee> viewEmployee() {
		ResultSet resultSet = employeeDaoImpl.getEmployees();
		List<Employee> employeeList = new ArrayList<Employee>();
	    try {
			while(resultSet.next()) {
			    Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setAge(resultSet.getInt(3));
				employee.setSalary(resultSet.getFloat(4));
				employee.setJoiningDate(resultSet.getString(5));
				List<String> skillList = new ArrayList<String>();
				ResultSet skillResultSet = employeeDaoImpl.getSkills(resultSet.getInt(1));
				while(skillResultSet.next()){
					  skillList.add(skillResultSet.getString(1));
			    }
				employee.setSkillList(skillList);
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return employeeList;
	}

	public void createEmployee(Employee employee) {
		employeeDaoImpl.addEmployee(employee);
		return;	
	}

	public void updateEmployee(Employee employee) {
		employeeDaoImpl.updateEmployee(employee);
		List<String> updatedSkillList = employee.getSkillList();
		List<String> databaseSkillList = new ArrayList<String>();
		ResultSet skillResultSet = employeeDaoImpl.getSkills(employee.getEmployeeId());
		
		try {
			while(skillResultSet.next()){
				databaseSkillList.add(skillResultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(String skill : updatedSkillList) {
			if(!databaseSkillList.contains(skill)){
				employeeDaoImpl.addSkill(employee.getEmployeeId(),skill);
			}
		}
		for(String skill : databaseSkillList) {
			if(!updatedSkillList.contains(skill)){
				employeeDaoImpl.deleteSkill(employee.getEmployeeId(),skill);
			}
		}
		return;
	}
}
