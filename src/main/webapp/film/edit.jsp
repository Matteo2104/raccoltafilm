<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Regista</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica Regista</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteEditFilmServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="titolo" class="form-label">Titolo </label>
									<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo" value="${edit_film_attr.titolo}" >
								</div>
								
								<div class="col-md-6">
									<label for="genere" class="form-label">Genere </label>
									<input type="text" name="genere" id="genere" class="form-control" placeholder="Inserire il genere" value="${edit_film_attr.genere}" >
								</div>
							
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${edit_film_attr.dataPubblicazione}' />
								<div class="col-md-3">
									<label for="dataPubblicazione" class="form-label">Data di Pubblicazione </label>
                        			<input class="form-control" id="dataPubblicazione" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataPubblicazione" value="${parsedDate}"  >
								</div>
					
								<div class="col-md-6">
									<label for="minutiDurata" class="form-label">Durata in minuti </label>
									<input type="text" class="form-control" name="minutiDurata" id="minutiDurata" placeholder="Inserire la durata in minuti" value="${edit_film_attr.minutiDurata}" >
								</div>	
										
								<div class="col-md-6">
									<label for="regista.id" class="form-label">Regista</label>
								    <select class="form-select" id="regista.id" name="regista.id">
								    	<option value="${edit_film_attr.regista.id}" selected> ${edit_film_attr.regista.nome} ${edit_film_attr.regista.cognome} </option>
								      	<c:forEach items="${registi_list_attr}" var="registaItem">
								      		<option value="${registaItem.id}" >${registaItem.nome } ${registaItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								
							<div class="col-12">
								<button type="submit" name="idFilm" value="${edit_film_attr.id}" class="btn btn-primary">Conferma</button>
								<a class="btn btn-outline-primary ml-2" href="PrepareInsertRegistaServlet">Add New</a>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>