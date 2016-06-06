package persistence;

import java.util.List;

import model.Esame;
import model.Studente;

public interface PianoDiStudiRepository {
	boolean insert(int studente, Long esame) throws PersistenceException;
	boolean deleteByStudente(int studente) throws PersistenceException;
	boolean deleteEsameByStudente(Studente studente, Esame esame) throws PersistenceException;
	boolean update(Studente studente, Esame esamevecchio, Esame esamenuovo) throws PersistenceException;

	public List<Long> findByStudente(int studente) throws PersistenceException;
	public List<Integer> findByEsame(Esame esame) throws PersistenceException;
	public List<Integer> findByAll() throws PersistenceException;

}
