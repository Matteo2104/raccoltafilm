package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;


@WebServlet("/ExecuteEditFilmServlet")
public class ExecuteEditFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ExecuteEditFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idFilm = request.getParameter("idFilm");
		
		
		if (!NumberUtils.isCreatable(idFilm)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		
		// estraggo input
		String titoloInputParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String dataPubblicazioneInputStringParam = request.getParameter("dataPubblicazione");
		String minutiDurataStringParam = request.getParameter("minutiDurata");
		String registaIdStringParam = request.getParameter("regista.id");

		
		
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Film filmInstance = UtilityForm.createFilmFromParams(titoloInputParam, genereInputParam,
				minutiDurataStringParam, dataPubblicazioneInputStringParam, registaIdStringParam);

		
		
		try {
			
		// se la validazione non risulta ok
		if (!UtilityForm.validateFilmBean(filmInstance)) {
			request.setAttribute("edit_film_attr", filmInstance);
			request.setAttribute("registi_list_attr", MyServiceFactory.getRegistaServiceInstance().listAllElements());
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
			return;
		}
		
		// assegno l'id
		filmInstance.setId(Long.parseLong(idFilm));
		
		
		System.out.println(filmInstance);
				
		
			MyServiceFactory.getFilmServiceInstance().aggiorna(filmInstance);
			
			request.setAttribute("film_list_attribute", MyServiceFactory.getFilmServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/film/list.jsp").forward(request, response);
	}

}
