package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.exceptions.ElementNotFoundException;
import it.prova.raccoltafilm.model.StatoUtente;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;


@WebServlet("/utente/ExecuteDeleteUserServlet")
public class ExecuteDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");

		if (!NumberUtils.isCreatable(idUser)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idUser));

			utenteInstance.setStato(StatoUtente.DISABILITATO);
			
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteInstance);
		} catch (ElementNotFoundException e) {
			request.getRequestDispatcher("ExecuteListFilmServlet?operationResult=NOT_FOUND").forward(request, response);
			return;
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListUserServlet?operationResult=SUCCESS");
	}

}
