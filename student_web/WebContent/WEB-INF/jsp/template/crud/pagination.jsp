<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav aria-label="Page navigation">
	
	<ul class="pagination justify-content-center">${pagination.currentPage == pagination.maxPage ?  pagination.totalRows : pagination.currentPage * pagination.pageSize} de ${pagination.totalRows} registros y ${pagination.currentPage} de ${pagination.maxPage} paginas</ul>
	<ul class="pagination justify-content-center">
		
		<li class="page-item ${pagination.currentPage == 1 ? 'disabled' : ''}">
     		<a class="page-link" value="1" tabindex="-1">Primera</a>
   		</li>
   		
		<li class="page-item ${pagination.currentPage == pagination.previousPage ? 'disabled' : ''}">
     		<a class="page-link" value="${pagination.previousPage}" tabindex="-1">Anterior</a>
   		</li>
   		
		<c:forEach begin="${pagination.start}" end="${pagination.end}" varStatus="loop">
		    <li class="page-item"><a class="page-link" value="${loop.index}" tabindex="-1">${loop.index}</a></li>
		</c:forEach>
		
    	<li class="page-item ${pagination.currentPage == pagination.maxPage ? 'disabled' : ''}">
      		<a class="page-link" value="${pagination.next}" tabindex="-1">Siguiente</a>
    	</li>
    	
    	<li class="page-item ${pagination.currentPage == pagination.maxPage ? 'disabled' : ''}">
     		<!--href="${pagination.maxPage}"  -->
     		<a class="page-link" value="${pagination.maxPage}" tabindex="-1">Ultima</a> 
   		</li>
   		
	</ul>
</nav>

