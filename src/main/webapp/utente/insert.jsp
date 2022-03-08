<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Inserisci Nuovo Utente</title>
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
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci nuovo utente</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="ExecuteInsertUserServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${insert_utente_attr.nome }" required>
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${insert_utente_attr.cognome }" required>
								</div>
							
								<div class="col-md-6">
									<label for="username" class="form-label">Username <span class="text-danger">*</span></label>
									<input type="text" class="form-control" name="username" id="username" placeholder="Inserire lo username" value="${insert_utente_attr.username }" required>
								</div>
					
								<div class="col-md-6">
									<label for="password" class="form-label">Password <span class="text-danger">*</span></label>
									<input type="text" class="form-control" name="password" id=password placeholder="Inserire la password" value="${insert_utente_attr.password }" required>
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_utente_attr.dateCreated}' />
								<div class="col-md-3">
									<label for="dataDiNascita" class="form-label">Data di Creazione <span class="text-danger">*</span></label>
                        			<input class="form-control" id="dateCreated" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dateCreated" required value="${parsedDate}" >
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
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
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