package it.prova.raccoltafilm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Sesso;
import it.prova.raccoltafilm.model.StatoUtente;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;

public class UtilityForm {

	public static Regista createRegistaFromParams(String nomeInputParam, String cognomeInputParam,
			String nickNameInputParam, String dataDiNascitaStringParam, String sessoParam) {

		Regista result = new Regista(nomeInputParam, cognomeInputParam, nickNameInputParam);
		result.setSesso(StringUtils.isBlank(sessoParam)?null:Sesso.valueOf(sessoParam));
		result.setDataDiNascita(parseDateArrivoFromString(dataDiNascitaStringParam));
		return result;
	}

	public static boolean validateRegistaBean(Regista registaToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(registaToBeValidated.getNome())
				|| StringUtils.isBlank(registaToBeValidated.getCognome())
				|| StringUtils.isBlank(registaToBeValidated.getNickName()) 
				|| registaToBeValidated.getSesso() == null
				|| registaToBeValidated.getDataDiNascita() == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername()) 
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())
				|| utenteToBeValidated.getDateCreated() == null) {
			return false;
		}
		return true;
	}
	
	public static Utente createUserFromParams(String nome, String cognome, String username, String dataCreazione) {
		Utente result = new Utente(nome, cognome, username);
		result.setDateCreated(parseDataCreazioneFromString(dataCreazione));
		return result;
	}
	
	public static Utente createUserFromParams(String nome, String cognome, String username, String password, String dataCreazione, String[] ruoli) {
		Utente result = new Utente(nome, cognome, username, password);
		result.setDateCreated(parseDataCreazioneFromString(dataCreazione));
		
		if (ruoli == null || ruoli.length < 1) {
			result.setRuoli(null);
		} else {
			for (int i=0;i<ruoli.length;i++) {
				try {
					result.getRuoli().add(MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(Long.parseLong(ruoli[i])));
				} catch (Exception e) {
					
				}
			}
		}
		
		result.setStato(StatoUtente.CREATO);
		
		return result;
	}
	
	public static Film createFilmFromParams(String titoloInputParam, String genereInputParam,
			String minutiDurataInputParam, String dataPubblicazioneStringParam, String registaIdStringParam) {

		Film result = new Film(titoloInputParam, genereInputParam);
		if (NumberUtils.isCreatable(minutiDurataInputParam)) {
			result.setMinutiDurata(Integer.parseInt(minutiDurataInputParam));
		}
		result.setDataPubblicazione(parseDateArrivoFromString(dataPubblicazioneStringParam));
		try {
			if (NumberUtils.isCreatable(registaIdStringParam)) {
				result.setRegista(MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(registaIdStringParam)).get());
			}
		} catch (Exception e) {
			
		}
		
		return result;
	}

	public static boolean validateFilmBean(Film filmToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(filmToBeValidated.getTitolo())
				|| StringUtils.isBlank(filmToBeValidated.getGenere())
				|| filmToBeValidated.getMinutiDurata() == null 
				|| filmToBeValidated.getMinutiDurata() < 1
				|| filmToBeValidated.getRegista() == null
				|| filmToBeValidated.getRegista().getId() == null 
				|| filmToBeValidated.getRegista().getId() < 1) {
			return false;
		}
		return true;
	}

	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parseDataCreazioneFromString(String data) {
		if (StringUtils.isBlank(data))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(data);
		} catch (ParseException e) {
			return null;
		}
	}
}
