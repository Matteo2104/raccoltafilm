<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		
							<form method="post" action="ExecuteEditRegistaServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome </label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${edit_regista_attr.nome}" >
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome </label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${edit_regista_attr.cognome}" >
								</div>
							
								<div class="col-md-6">
									<label for="nickName" class="form-label">Nickname </label>
									<input type="text" class="form-control" name="nickName" id="nickName" placeholder="Inserire il nickname" value="${edit_regista_attr.nickName}" >
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${edit_regista_attr.dataDiNascita}' />
								<div class="col-md-3">
									<label for="dataDiNascita" class="form-label">Data di Nascita </label>
                        			<input class="form-control" id="dataDiNascita" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataDiNascita" value="${parsedDate}"  >
								</div>
								
								<div class="col-md-3">
									<label for="sesso" class="form-label">Sesso </label>
								    <select class="form-select" id="sesso" name="sesso" >
								    	<c:if test="${edit_regista_attr.sesso != null}" >
								    		<option value="${edit_regista_attr.sesso}" selected> ${edit_regista_attr.sesso} </option>
								    	</c:if>
								    	<c:if test="${edit_regista_attr.sesso == null}" >
								    		<option value="" selected> - Selezionare - </option>
								    	</c:if>
								      	<option value="MASCHIO" >MASCHIO</option>
								      	<option value="FEMMINA"  >FEMMINA</option>
								    </select>
								</div>
								
								
							<div class="col-12">
								<button type="submit" name="idRegista" value="${edit_regista_attr.id}" class="btn btn-primary">Conferma</button>
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