<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<form class="form-${idModal}" novalidate>
	<c:if  test="${element.elementType.name=='hidden'}">
		<input id="${element.idelement}" name="${element.idelement}" type="hidden" value="">
	</c:if>
	<div id="message"></div>
</form>
	