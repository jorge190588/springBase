<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-striped table-bordered">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Email</th>
		<th>Habilitado</th>
		<th>Role</th>
		<th>Created at</th>
		<th>Updated at</th>
		<th>Options</th>
	</tr>
	<c:forEach var="item" items="${listData}">
		<tr>
			<td>${item.id }</td>
			<td>${item.name }</td>
			<td>${item.email }</td>
			<td>${item.isEnabled == 'true' ? 'Si' : 'No'}</td>
			<td>${item.roles.name }</td>
			<td>${item.createdAt }</td>
			<td>${item.updatedAt }</td>
			<td>
				<button type="button" id="update" elementId="${item.id}" class="btn btn-outline-info btn-sm">Editar</button>
				<button type="button" id="delete" elementId="${item.id}" class="btn btn-outline-danger btn-sm">Eliminar</button>
			</td>
		</tr>
	</c:forEach>
</table>
