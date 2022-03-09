package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;


@WebServlet("/utente/PrepareDeleteUserServlet")
public class PrepareDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public PrepareDeleteUserServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
		
		if (!NumberUtils.isCreatable(idUser)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idUser));

			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListUserServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			
			System.out.println(utenteInstance);

			request.setAttribute("delete_utente_attr", utenteInstance);
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/utente/delete.jsp").forward(request, response);
	}


}
