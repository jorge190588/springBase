<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>First example</title>
</head>
<body>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>FirstName</tr>
		</tr>
		<c:forEach var="student" items="${listStudents }">
			<tr>
				<td>${student.firstName }</td>
			</tr>
		</c:forEach>
	</table>
			
</body>
</html>