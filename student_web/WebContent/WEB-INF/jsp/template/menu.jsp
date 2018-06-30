<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/logout" var="logoutUrl" />
	<form id="logout" action="${logoutUrl}" method="post" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
<div class="box-shadow">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href=".">
	  		<i class="fa fa-graduation-cap fa-lg"></i> Student App
	  	</a>
	  	<button 	class="navbar-toggler" type="button" 
	  				data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" 
	  				aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  
	  	<div class="collapse navbar-collapse" id="navbarNavDropdown">
	    
	    <ul class="navbar-nav mr-auto">
	    	<li class="nav-item active">
	        	<a class="nav-link" href="#">
		        	<i class="fa fa-home fa-sm"></i> Home <span class="sr-only">(current)</span>
		        </a>
	    	</li>
			
			<li class="nav-item active">
	        	<a class="nav-link" href="#">
	        		<i class="fa fa-bell fa-sm"></i> Indicadores <span class="sr-only">(current)</span>
	        	</a>
	      	</li>
	      	<li class="nav-item">
	        	<a class="nav-link" href="#">
	        		<i class="fa fa-edit fa-sm"></i> Actualizar indicadores
	        	</a>
			</li>
		    <li class="nav-item">
	        	<a class="nav-link" href="#"><i class="fa fa-chart-line fa-sm"></i> Dashboard</a>
	      	</li>
			<li class="nav-item dropdown">
	        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          		<i class="fa fa-table fa-sm"></i> Tablas
	        	</a>
	        	
	        	<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	          		<a class="dropdown-item" href="student">Estudiante</a>
	          		<a class="dropdown-item" href="#">Profesor</a>
	          		<a class="dropdown-item" href="#">Curso</a>
	        	</div>
			</li>
	      
			<li class="nav-item dropdown">
	        	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          		<i class="fa fa-user-lock fa-sm"></i> Seguridad
	        	</a>
	        	<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	          		<a class="dropdown-item" href="user"><i class="fa fa-user fa-sm"></i> Usuarios</a>
	          		<a class="dropdown-item" href="rol"><i class="fa fa-users fa-sm"></i> Roles</a>
	          		<a class="dropdown-item" href="module"><i class="fa fa-users fa-sm"></i> Modulo</a>
	          		<a class="dropdown-item" href="form"><i class="fa fa-users fa-sm"></i> Formulario</a>
	          		<a class="dropdown-item" href="element"><i class="fa fa-users fa-sm"></i> Elemento</a>
	        	</div>
	      	</li>      
		</ul>
	    
	    <ul class="nav navbar-nav">
			<li class="nav-item">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<a class="nav-link" href="javascript:document.getElementById('logout').submit()"><i class="fas fa-sign-out-alt"></i> Cerrar session</a>
			</c:if>
	        
	      	</li>
		</ul>
	            
	  </div>   
	</nav>
</div>