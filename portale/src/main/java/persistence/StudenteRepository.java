package persistence;

import java.util.List;

import model.Studente;

public interface StudenteRepository {
	boolean insert(Studente studente) throws PersistenceException;
	boolean update(Studente studente, String nome, String cognome) throws PersistenceException;
	boolean delete(Studente studente) throws PersistenceException;
	Studente findByMatricola(int matricola) throws PersistenceException;
	List<Studente> findByNome(String nome) throws PersistenceException;
	List<Studente> findByCognome(String cognome) throws PersistenceException;

}
