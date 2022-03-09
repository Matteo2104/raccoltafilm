package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;


import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;
import it.prova.raccoltafilm.model.Regista;


@WebServlet("/ExecuteEditRegistaServlet")
public class ExecuteEditRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ExecuteEditRegistaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRegista = request.getParameter("idRegista");

		
		
		if (!NumberUtils.isCreatable(idRegista)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		
		// estraggo input
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String nickNameInputStringParam = request.getParameter("nickName");
		String dataDiNascitaStringParam = request.getParameter("dataDiNascita");
		String sessoStringParam = request.getParameter("sesso");

		
		
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Regista registaInstance = UtilityForm.createRegistaFromParams(nomeInputParam, cognomeInputParam,
				nickNameInputStringParam, dataDiNascitaStringParam, sessoStringParam);

		
		// se la validazione non risulta ok
		if (!UtilityForm.validateRegistaBean(registaInstance)) {
			request.setAttribute("edit_regista_attr", registaInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
			return;
		}
		
		// assegno l'id
		registaInstance.setId(Long.parseLong(idRegista));
		
		//System.out.println(registaInstance);
				
		try {
			MyServiceFactory.getRegistaServiceInstance().aggiorna(registaInstance);
			
			request.setAttribute("registi_list_attribute", MyServiceFactory.getRegistaServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
	}

}
