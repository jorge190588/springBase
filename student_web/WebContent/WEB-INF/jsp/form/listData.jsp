<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-striped table-bordered">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Title</th>
		<th>Url</th>
		<th>Orden</th>
		<th>Icono</th>
		<th>Module</th>
		<th>Created at</th>
		<th>Updated at</th>
		<th>Options</th>
	</tr>
	<c:forEach var="item" items="${listData}">
		<tr>
			<td>${item.id }</td>
			<td>${item.name }</td>
			<td>${item.title }</td>
			<td>${item.url}</td>
			<td>${item.orderElement}</td>
			<td><i class="${item.icon}"></i> ${item.icon}
			</td>
			<td>${item.module.name }</td>
			<td>${item.createdAt }</td>
			<td>${item.updatedAt }</td>
			<td>
				<button type="button" id="update" elementId="${item.id}" class="btn btn-outline-info btn-sm">Editar</button>
				<button type="button" id="delete" elementId="${item.id}" class="btn btn-outline-danger btn-sm">Eliminar</button>
			</td>
		</tr>
	</c:forEach>
</table>
