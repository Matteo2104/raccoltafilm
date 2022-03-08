package it.prova.raccoltafilm.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;

public interface IBaseDAO<T> {

	public List<T> list() throws Exception;

	public Optional<T> findOne(Long id) throws Exception;

	public void update(T input) throws Exception;

	public void insert(T input) throws Exception;

	public void delete(T input) throws Exception;
	
	public List<T> findByExample(T example) throws Exception;

	// questo mi serve per l'injection
	public void setEntityManager(EntityManager entityManager);

	

}
