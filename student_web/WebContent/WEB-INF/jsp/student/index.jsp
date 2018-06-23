<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${ApplicationName}</title>
	<jsp:include page="../template/styles.jsp" />
</head>
<body>
	<jsp:include page="../template/menu.jsp" />
	
	<!-- page content -->
	<div class="container-fluid">
		<div class="row justify-content-md-center">
			<div class="col-lg-1"></div>
	    	<div class="col-lg-10">
	    		<div class="my-3 p-3 bg-white rounded box-shadow">
	    			<jsp:include page="../template/pageTitle.jsp" />
	    			<jsp:include page="../template/navOptions.jsp" />
					<jsp:include page="list.jsp" />
				</div>
	    	</div>
	    	<div class="col-lg-1"></div>
		</div>
	</div>
	
	<!-- create form -->
	<c:set var="idModal" value="create" scope="session"/>
	<c:set var="title" value="Crear registro" scope="session"/>
	<c:set var="isCreate" value="true" scope="session"/>
	<c:set var="isUpdate" value="false" scope="session"/>
	<c:set var="isDelete" value="false" scope="session"/>
	<jsp:include page="../template/modal/header.jsp" />
	<jsp:include page="createFormContent.jsp" />
	<jsp:include page="../template/modal/footer.jsp" />
	
	
	<!-- update form -->
	<c:set var="idModal" value="update" scope="session"/>
	<c:set var="title" value="Actualizar registro" scope="session"/>
	<c:set var="isCreate" value="false" scope="session"/>
	<c:set var="isUpdate" value="true" scope="session"/>
	<c:set var="isDelete" value="false" scope="session"/>
	<jsp:include page="../template/modal/header.jsp" />
	<jsp:include page="createFormContent.jsp" />
	<jsp:include page="../template/modal/footer.jsp" />
	
	
	<!-- delete form -->
	<c:set var="idModal" value="delete" scope="session"/>
	<c:set var="title" value="Eliminar registro" scope="session"/>
	<c:set var="isCreate" value="false" scope="session"/>
	<c:set var="isUpdate" value="false" scope="session"/>
	<c:set var="isDelete" value="true" scope="session"/>
	<jsp:include page="../template/modal/header.jsp" />
	<jsp:include page="createFormContent.jsp" />
	<jsp:include page="../template/modal/footer.jsp" />
	
	
	<!-- Template footer -->
	<jsp:include page="../template/footer.jsp" />
	<jsp:include page="../template/jsScripts.jsp" />

	<script src="assets/js/general/listComponent.js"></script>
	<script src="assets/js/general/navOptions.js"></script>
	<script src="assets/js/general/formValidations.js"></script>
	<script src="assets/js/general/formValues.js"></script>
	<script src="assets/js/student/index.js"></script>
</body>
</html>