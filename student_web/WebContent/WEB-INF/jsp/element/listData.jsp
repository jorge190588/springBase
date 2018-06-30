<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table table-striped table-bordered">
	<tr>
		<th>Id</th>
		<th>Elemento</th>
		<th>Entidad</th>
		<th>Etiqueta</th>
		<th>Tipo</th>
		<th>Patron</th>
		<th>Patron Mensaje</th>
		<th>Requerido / Unico</th>
		<th>CRUD</th>
		<th>Options</th>
	</tr>
	<c:forEach var="item" items="${listData}">
		<tr>
			<td>${item.id }</td>
			<td>${item.idelement}</td>
			<td>${item.entiti.name }</td>
			<td>${item.label}</td>
			<td>${item.elementType.name }</td>
			<td>${item.pattern }</td>
			<td>${item.patternMessage }</td>
			<td>
				<c:if test="${item.isUnique eq true}">
					<span class="badge badge-light">Unico</span>
				</c:if>
				<c:if test="${item.isRequired eq true}">
					<span class="badge badge-warning">Requerido</span>
				</c:if>
			</td>
			 
			<td>
				<c:if test="${item.isCreate eq true}">
					<span class="badge badge-success">create</span>
				</c:if>
				<c:if test="${(item.isUpdate eq true)}">
					<span class="badge badge-primary">update</span>
				</c:if>
				<c:if test="${(item.isDelete eq true)}">
					<span class="badge badge-danger">delete</span>
				</c:if>
				<span class="badge badge-dark">orden ${item.orderElement}</span>
			</td>
			<td>
				<button type="button" id="update" elementId="${item.id}" class="btn btn-outline-info btn-sm">Edit</button>
				<button type="button" id="delete" elementId="${item.id}" class="btn btn-outline-danger btn-sm">Elim</button>
			</td>
		</tr>
	</c:forEach>
</table>
