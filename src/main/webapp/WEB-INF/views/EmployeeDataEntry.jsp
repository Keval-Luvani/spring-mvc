<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Insert Data</title>
	</head>
	<body>
		<form:form action="submit" method="post" modelAttribute="employee">
			<form:input path="employeeId" type="hidden" />
			
			<form:label path="name">Name</form:label>
			<form:input path="name" required="required"/><br>
			
			<form:label path="age" >age</form:label>
			<form:input path="age" type="number" required="required"/><br>
			
			<form:label path="salary">salary</form:label>
			<form:input path="salary" type="number" step="0.01" required="required"/><br>
			
			<form:label path="skillList">Skills : </form:label>
			<form:checkbox path="skillList" value="Java" label="Java" /> 
            <form:checkbox path="skillList" value="Python" label="Python" /> 
			<form:checkbox path="skillList" value="Node Js" label="Node Js"/> 
            <form:checkbox path="skillList" value="PHP" label="PHP" /> 
            <form:checkbox path="skillList" value="React" label="React"/> 
            <form:checkbox path="skillList" value="Angular" label="Angular" />
            <form:checkbox path="skillList" value="SQL" label="SQL"/> <br>
           
			<form:label path="joiningDate">Joining Date</form:label>
			<form:input path="joiningDate" type="date" required="required" max="${todayDate}"/><br>
			
			<form:button>Submit</form:button>
		</form:form>
	</body>
</html>