<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-striped table-bordered">
	<tr>
		<th>Id</th>
		<th>First name</th>
		<th>Second name</th>
		<th>Carne</th>
		<th>Created at</th>
		<th>Updated at</th>
	</tr>
	<c:forEach var="student" items="${listStudents}">
		<tr>
			<td>${student.id }</td>
			<td>${student.firstName }</td>
			<td>${student.lastName }</td>
			<td>${student.carne }</td>
			<td>${student.createdAt }</td>
			<td>${student.updatedAt }</td>
		</tr>
	</c:forEach>
</table>
