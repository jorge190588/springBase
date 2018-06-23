<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<form class="form-${idModal}" novalidate>
	<c:forEach var="element" items="${elementList}">
		
		<c:set var="renderElement" value="false" scope="session"/>
		<c:if test="${(isCreate eq true) and (element.isCreate eq true)}">
			<c:set var="renderElement" value="true" scope="session"/>
		</c:if>
		<c:if test="${(isUpdate eq true) and (element.isUpdate eq true)}">
			<c:set var="renderElement" value="true" scope="session"/>
		</c:if>
		<c:if test="${(isDelete eq true) and (element.isDelete eq true)}">
			<c:set var="renderElement" value="true" scope="session"/>
		</c:if>
		
		<c:if test="${(renderElement eq true)}">
			
			<c:if  test="${element.elementType.name=='hidden'}">
				<input id="${element.idelement}" name="${element.idelement}" type="hidden" value="">
			</c:if>
			
			<c:if  test="${element.elementType.name=='h3'}">
				<h3 id="${element.idelement}" value="${element.label}">${element.label}</h3>
			</c:if>
			
			<c:if  test="${element.elementType.name=='input'}">
	
			 	  <div class="form-row">
	    			<label for="recipient-name" class="col-form-label">${element.label}</label>
	    			<c:set var="requiredProperty" value="" scope="session"/>
	    			
	    			<c:if test="${ element.isRequired}"> 
	    				<c:set var="requiredProperty" value="required" scope="session"/>
	    			</c:if>
	    			
	     			<input type="text" class="form-control" id="${element.idelement}" placeholder="${element.label}"  ${requiredProperty} pattern="${fn:length(element.pattern) > 0 ? element.pattern : ''}">
	     			
	     			<c:if test="${ element.isRequired}"> 
	     				<div class="invalid-feedback">
	     					${fn:length(element.pattern) > 0 ? element.patternMessage : 'Ingrese un valor valido para '.concat(element.label)}
	      				</div>
	    			</c:if>
    			 </div>
	
			</c:if>
		</c:if>		
	</c:forEach>
</form>
   