package persistence;

import java.util.List;

import model.Esame;

public interface EsameRepository {
	boolean insert(Esame esame) throws PersistenceException;
	boolean update(Esame esame, String nome, String descrizione, int cfu) throws PersistenceException;
	boolean delete(Esame esame) throws PersistenceException;

	Esame findById(long id) throws PersistenceException;
	List<Esame> findByNome(String nome) throws PersistenceException;
	List<Esame> findAll() throws PersistenceException;
}
