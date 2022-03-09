package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.service.MyServiceFactory;


@WebServlet("/utente/PrepareSearchUserServlet")
public class PrepareSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareSearchUserServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("list_utente_role_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione operazione non andata a buon fine");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/utente/search.jsp").forward(request, response);
	}

}
