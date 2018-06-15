<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<form class="form-${idModal}" novalidate>
	 
		<c:forEach var="element" items="${elementList}">
		
			<c:if  test="${element.type=='input'}">
	
			 	  <div class="form-row">
	    			<label for="recipient-name" class="col-form-label">${element.label}</label>
	     			<input type="text" class="form-control" id="${element.idElement}" placeholder="${element.label}" ${element.required} pattern="${fn:length(element.pattern) > 0 ? element.pattern : ''}">
	     			<c:if  test="${element.required=='required'}">
	     				<div class="invalid-feedback">
	     					${fn:length(element.pattern) > 0 ? element.patternMessage : 'Ingrese un valor valido para '.concat(element.label)}
	      				</div>
	    	
	    			</c:if>
    			 </div>
	
			</c:if>
		
		</c:forEach>
	 
</form>
   