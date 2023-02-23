package com.keval.SpringCrudApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.keval.SpringCrudApp.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	
	Connection connection;
	
	public  static Connection initializeDatabase() throws SQLException, ClassNotFoundException
    {
        String databaseDriver = "com.mysql.jdbc.Driver";
        String databaseURL = "jdbc:mysql://localhost:3306/";
        String databaseName = "employee";
        String databaseUsername = "root";
        String databasePassword = "root";
  
        Class.forName(databaseDriver);
        Connection connection = DriverManager.getConnection(databaseURL + databaseName, databaseUsername, databasePassword);
        return connection;
    }	
	
	public ResultSet getEmployees() {
		try {
			 if(connection==null) {
				 connection = initializeDatabase();
			 }
		     String query = "select * from employee";
		     PreparedStatement preparedStatement = connection.prepareStatement(query);
		     ResultSet resultSet = preparedStatement.executeQuery();
		     return resultSet;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet getEmployee(int employeeId) {
		try {
			if(connection==null) {
				 connection = initializeDatabase();
			}
			String query = "select * from employee where employee_id=?";     
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1,employeeId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	       return resultSet;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addEmployee(Employee employee) {
		
		try {
			if(connection==null) {
				 connection = initializeDatabase();
			}
			String query = "insert into employee (name,age,salary,joining_date) values(?,?,?,?)";
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, employee.getName());
	        preparedStatement.setInt(2,employee.getAge());
	        preparedStatement.setFloat(3, employee.getSalary());
	        preparedStatement.setString(4, employee.getJoiningDate());
	        preparedStatement.execute();
	        ResultSet resultsetid = preparedStatement.getGeneratedKeys();
	        
	        int id=0;
	        while(resultsetid.next()) {
	        	id = resultsetid.getInt(1);
	        }
	        
	        for(String skill:employee.getSkillList()) {
				addSkill(id,skill);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployee(Employee employee) {
		 try {
			if(connection==null) {
				connection = initializeDatabase();
			}
	        String query = "update employee set name=?,age=?,salary=?,joining_date=? where employee_id = ?";
	        PreparedStatement prepareStatement = connection.prepareStatement(query);
	        prepareStatement.setString(1, employee.getName());
	        prepareStatement.setInt(2,employee.getAge());
	        prepareStatement.setFloat(3, employee.getSalary());
	        prepareStatement.setString(4, employee.getJoiningDate());
	        prepareStatement.setInt(5,employee.getEmployeeId());
	        prepareStatement.executeUpdate();
        }catch(SQLException e){
        	e.printStackTrace();
        }catch (ClassNotFoundException e) {
			e.printStackTrace();
        }
	}

	public void deleteEmployee(int employeeId) {
		try {
			if(connection==null) {
				connection = initializeDatabase();
			}
	        String query = "delete from employee where employee_id=?";
	        PreparedStatement prepareStatement = connection.prepareStatement(query);
	        prepareStatement.setInt(1, employeeId);
	        prepareStatement.executeUpdate();
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addSkill(int employeeId,String skill) {
        try{
        	if(connection==null) {
				 connection = initializeDatabase();
			}
            String query = "insert into skills (employee_id,skill) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,employeeId);
            preparedStatement.setString(2,skill);
	    	preparedStatement.executeUpdate();
	    }catch(Exception ex){
	    	  ex.printStackTrace();
	    }
	}
	
	public void deleteSkill(int employeeId,String skill) {
        try{
        	if(connection==null) {
				 connection = initializeDatabase();
			}
            String query = "delete from skills where employee_id=? AND skill=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,employeeId);
            preparedStatement.setString(2,skill);
	    	preparedStatement.executeUpdate();
        }catch(SQLException e){
        	e.printStackTrace();
        }catch (ClassNotFoundException e) {
			e.printStackTrace();
        }
	}

	public ResultSet getSkills(int employeeId) {
		try{
        	if(connection==null) {
			 connection = initializeDatabase();
        	}
            String query = "select skill from skills where employee_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,employeeId);
	    	ResultSet resultSet = preparedStatement.executeQuery();	
	    	return resultSet;
	    }catch(Exception ex){
	    	  ex.printStackTrace();
	    }
		return null;
	}
}
