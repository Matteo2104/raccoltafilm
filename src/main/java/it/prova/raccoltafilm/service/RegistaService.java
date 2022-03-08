package it.prova.raccoltafilm.service;

import java.util.List;
import java.util.Optional;

import it.prova.raccoltafilm.dao.RegistaDAO;
import it.prova.raccoltafilm.model.Regista;

public interface RegistaService {
	
	public List<Regista> listAllElements() throws Exception;

	public Optional<Regista> caricaSingoloElemento(Long id) throws Exception;
	
	public Regista caricaSingoloElementoConFilms(Long id) throws Exception;

	public void aggiorna(Regista registaInstance) throws Exception;

	public void inserisciNuovo(Regista registaInstance) throws Exception;

	public void rimuovi(Regista registaInstance) throws Exception;
	
	public List<Regista> findByExample(Regista example) throws Exception;

	//per injection
	public void setRegistaDAO(RegistaDAO registaDAO);

	void rimuovi(Long idRegistaToRemove) throws Exception;

	Regista caricaSingoloElementoEager(Long id) throws Exception;

	

}
