package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;


import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;


@WebServlet("/utente/PrepareEditUserServlet")
public class PrepareEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareEditUserServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUser = request.getParameter("idUser");
		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		try {
			// carico un utente eager
			Utente utente = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElementoEager(Long.parseLong(idUser));
			
			// carico su request l'utente
			request.setAttribute("edit_utente_attr", utente);
			
			// carico anche la lista di tutti i possibili ruoli
			request.setAttribute("list_utente_role_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
			
			// infine inserisco all'interno di una lista di stringhe gli id dei ruoli
			// relativi all'utente
			List<String> ruoliCheckedId = new ArrayList<>();
			for (Ruolo item : utente.getRuoli()) {
				ruoliCheckedId.add(item.getId().toString());
			}
			
			/*
			// only for test
			for (String item : ruoliCheckedId) {
				System.out.println(item);
			}
			System.out.println("\n");
			*/
			
			// ...e li carico su request
			request.setAttribute("list_utente_rolechecked_attr", ruoliCheckedId);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
		
	}

}
