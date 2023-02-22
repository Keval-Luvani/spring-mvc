<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
   	<head>
       <title>Employees</title>
    </head>
    <body>
    
       <h1>Employee Data</h1>
       <% int id=0; %>
       <table BORDER="1">
	      <tr>
		      <th>ID</th>
		      <th>Name</th>
		      <th>Age</th>
		      <th>Salary</th>
		      <th>Skills</th>
		      <th>Joining Date</th>
		      <th>Action</th>
	      </tr>
	      <c:forEach items="${employeeList}" var="employee" varStatus="rows">
    		<tr>
        	   <td>${rows.count}</td>
		       <td>${employee.getName()}</td>	
		       <td>${employee.getAge()} </td>
		       <td>${employee.getSalary()} </td>
		       <td>${String.join(",",employee.getSkillList())} </td>
		       <td>${employee.getJoiningDate()} </td>
		       <td>
		       	 <a href='<c:url value="update${employee.getEmployeeId()}"></c:url>'>Update </a>
		         <a href='<c:url value="delete${employee.getEmployeeId()}"></c:url>'>Delete </a>
		       </td>  
    		</tr>
		  </c:forEach>
	   </table>
       <a href='<c:url value="create"></c:url>'>Create Employee</a>	
     </body>
</html>