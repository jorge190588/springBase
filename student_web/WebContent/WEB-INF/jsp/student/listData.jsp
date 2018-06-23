<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-striped table-bordered">
	<tr>
		<th>Id</th>
		<th>First name</th>
		<th>Second name</th>
		<th>Carne</th>
		<th>Created at</th>
		<th>Updated at</th>
		<th>Options</th>
	</tr>
	<c:forEach var="student" items="${listStudents}">
		<tr>
			<td>${student.id }</td>
			<td>${student.firstName }</td>
			<td>${student.lastName }</td>
			<td>${student.carne}</td>
			<td>${student.createdAt }</td>
			<td>${student.updatedAt }</td>
			<td>
				<button type="button" id="update" elementId="${student.id}" class="btn btn-outline-info btn-sm">Editar</button>
				<button type="button" id="delete" elementId="${student.id}" class="btn btn-outline-danger btn-sm">Eliminar</button>
			</td>
		</tr>
	</c:forEach>
</table>
