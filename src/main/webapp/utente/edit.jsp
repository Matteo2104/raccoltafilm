<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Utente</title>
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
				        <h5>Modifica Utente</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteEditUserServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome: </label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${edit_utente_attr.nome}" >
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome: </label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${edit_utente_attr.cognome}" >
								</div>
								
								<div class="col-md-6">
									<label for="username" class="form-label">Username: </label>
									<input type="text" name="username" id="username" class="form-control" placeholder="Inserire lo username" value="${edit_utente_attr.username}" >
								</div>
								
								<div class="col-md-6">
									<label for="password" class="form-label">Password: </label>
									<input type="text" name="password" id="password" class="form-control" placeholder="Inserire la password" value="${edit_utente_attr.password}" >
								</div>
							
								<div class="col-md-3">
									<label for="dateCreated" class="form-label">Data di Creazione </label>
                        			<input class="form-control" id="dateCreated" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dateCreated" value="${edit_utente_attr.dateCreated}"  >
								</div>
										
								<c:forEach items="${list_utente_role_attr}" var="ruolo" >
									<c:if test="${list_utente_rolechecked_attr!=null}">
										<c:choose >
											<c:when test="${fn:contains(list_utente_rolechecked_attr, ruolo.id)}" >
												<div class="form-check">
													<input class="form-check-input" type="checkbox" value="${ruolo.id}"
													id="flexCheckDefault" name="ruoli" checked> <label class="form-check-label"
													for="flexCheckDefault"> ${ruolo.descrizione} </label>
												</div>
											</c:when>
											<c:otherwise>
												<div class="form-check">
													<input class="form-check-input" type="checkbox" value="${ruolo.id}"
													id="flexCheckDefault" name="ruoli"> <label class="form-check-label"
													for="flexCheckDefault"> ${ruolo.descrizione} </label>
												</div>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${list_utente_rolechecked_attr==null}">
												<div class="form-check">
													<input class="form-check-input" type="checkbox" value="${ruolo.id}"
													id="flexCheckDefault" name="ruoli"> <label class="form-check-label"
													for="flexCheckDefault"> ${ruolo.descrizione} </label>
												</div>
									</c:if>
								</c:forEach>
								
								<div class="col-12">
									<button type="submit" name="idUser" value="${edit_utente_attr.id}" class="btn btn-primary">Conferma</button>
									<a class="btn btn-outline-primary ml-2" href="PrepareInsertUserServlet">Add New</a>
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