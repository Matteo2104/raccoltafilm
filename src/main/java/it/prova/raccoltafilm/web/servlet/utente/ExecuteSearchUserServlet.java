package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/utente/ExecuteSearchUserServlet")
public class ExecuteSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteSearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("usernameParam");
		String dataCreazioneParam = request.getParameter("dataCreazioneParam");
		String[] ruoliParam = request.getParameterValues("ruoli");

		// creo un bean
		Utente example = UtilityForm.createUserFromParams(nomeParam, cognomeParam, usernameParam, dataCreazioneParam, ruoliParam);
		/*
		Utente example = new Regista(nomeParam, cognomeParam, usernameParam,
				UtilityForm.parseDateArrivoFromString(dataCreazioneParam));
		*/
		
		try {
			request.setAttribute("users_list_attribute",
					MyServiceFactory.getUtenteServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/utente/list.jsp").forward(request, response);
	}

}
