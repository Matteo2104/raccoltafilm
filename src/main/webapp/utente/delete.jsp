<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Disabilita Utente</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Sei sicuro di voler disabilitare questo utente?</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_utente_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nome:</dt>
							  <dd class="col-sm-9">${delete_utente_attr.nome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${delete_utente_attr.cognome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Username:</dt>
							  <dd class="col-sm-9">${delete_utente_attr.username}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Password:</dt>
							  <dd class="col-sm-9">${delete_utente_attr.password}</dd>
					    	</dl>
					    	
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Creazione:</dt>
							  <dd class="col-sm-9">${delete_utente_attr.dateCreated}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Stato attuale:</dt>
							  <dd class="col-sm-9">${delete_utente_attr.stato}</dd>
					    	</dl>
					    	
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Info Ruoli
							  </a>
							</p>
					    	
					    </div>
					    
							<!-- info ruoli -->
							<div class="collapse" id="collapseExample">
							<div class="card card-body">
								<c:forEach items="${visualizza_utente_attr.ruoli}" var="ruolo" >
									<dl class="row">
								  	<dt class="col-sm-3 text-right">Codice:</dt>
								  	<dd class="col-sm-9">${ruolo.codice}</dd>
							   		</dl>
							   		<dl class="row">
								 	 <dt class="col-sm-3 text-right">Descrizione:</dt>
								  	<dd class="col-sm-9">${ruolo.descrizione}</dd>
							   		</dl>
								</c:forEach>  
							  </div>
							 </div>
							 
					<!-- end card -->
					</div>	
					    
					    <div class='card-footer'>
					    	<form action="ExecuteDeleteUserServlet" method="post">
					    		<input type="hidden" name="idUser" value="${delete_utente_attr.id}">
						    	<button type="submit" name="submit" id="submit" class="btn btn-danger">Conferma</button>
						        <a href="ExecuteListUserServlet" class='btn btn-outline-secondary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Back
						        </a>
					        </form>
					    </div>
						
			  	
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>