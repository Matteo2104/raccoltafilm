package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareInsertUserServlet
 */
@WebServlet("/utente/PrepareInsertUserServlet")
public class PrepareInsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PrepareInsertUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("insert_utente_attr", new Utente());
			request.setAttribute("list_utente_role_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
	}

	

}
