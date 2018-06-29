<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${ApplicationName}</title>
	<jsp:include page="template/crud/styles.jsp" />
</head>
<body>
	<jsp:include page="template/menu.jsp" />
	
	<div class="container-fluid">
		<div class="row"></div>
		<div class="row justify-content-md-center">
			<div class="col-lg-2"></div>
	    	<div class="col-lg-8">
	    		<jsp:include page="template/pageTitle.jsp" />
	    	</div>
	    	<div class="col-lg-2"></div>
		</div>
	</div>
	
	<jsp:include page="template/footer.jsp" />
	<jsp:include page="template/scripts.jsp" />
		
</body>
</html>