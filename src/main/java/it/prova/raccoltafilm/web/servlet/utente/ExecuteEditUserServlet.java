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

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;


@WebServlet("/utente/ExecuteEditUserServlet")
public class ExecuteEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteEditUserServlet() {
        super();
    }

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");

		
		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String dateCreatedParam = request.getParameter("dateCreated");
		String[] ruoliParam = request.getParameterValues("ruoli");

		
		
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Utente utenteInstance = UtilityForm.createUserFromParams(nomeParam, cognomeParam,
				usernameParam, passwordParam, dateCreatedParam, ruoliParam);

		try {
			
			// eseguo la validazione (i campi non possono essere vuoti)
			if (!UtilityForm.validateUtenteBean(utenteInstance)) {
				request.setAttribute("edit_utente_attr", utenteInstance);
				request.setAttribute("list_utente_role_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
				
				List<String> ruoliCheckedId = new ArrayList<>();
				for (Ruolo item : utenteInstance.getRuoli()) {
					ruoliCheckedId.add(item.getId().toString());
				}
				request.setAttribute("list_utente_rolechecked_attr", ruoliCheckedId);
				
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
				return;
			}
			
			
			// assegno l'id
			utenteInstance.setId(Long.parseLong(idUser));
			
			//System.out.println(utenteInstance);
			
		
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteInstance);
			
			request.setAttribute("users_list_attribute", MyServiceFactory.getUtenteServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/utente/list.jsp").forward(request, response);
		
		//System.out.println("fin qui tutto ok");
	}

}
