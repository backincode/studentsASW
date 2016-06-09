package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import persistence.PersistenceException;
import persistence.postgres.EsameDAOpostgres;
import persistence.postgres.PianoDiStudiDAOpostgres;
import persistence.postgres.StudenteDAOpostgres;

public class FacadeImp implements Facade {

	@Override
	public boolean inserisciStudente(String nome, String cognome, int matricola) {
		
		try {
			StudenteDAOpostgres dao = new StudenteDAOpostgres();
			Studente studente = new Studente();
			studente.setNome(nome.toLowerCase());
			studente.setCognome(cognome.toLowerCase());
			studente.setMatricola(matricola);
			return dao.insert(studente);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean aggiornaStudente(Studente studente, String nome, String cognome){
		try {
			StudenteDAOpostgres dao = new StudenteDAOpostgres();
			return dao.update(studente,nome.toLowerCase(),cognome.toLowerCase());
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminaStudente(Studente studente) {
		try {
			StudenteDAOpostgres dao = new StudenteDAOpostgres();
			return dao.delete(studente);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	@Override
	public Studente trovaStudentePerMatricola(int matricola) {
		try {
			StudenteDAOpostgres dao = new StudenteDAOpostgres();
			return dao.findByMatricola(matricola);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Studente> trovaStudentePerNome(String nome) {
		try {
			StudenteDAOpostgres dao = new StudenteDAOpostgres();
			return dao.findByNome(nome.toLowerCase());
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Studente> trovaStudentePerCognome(String cognome) {
		try {
			StudenteDAOpostgres dao = new StudenteDAOpostgres();
			return dao.findByCognome(cognome.toLowerCase());
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean inserisciEsame(String nome, String descrizione, int cfu) {
		try {
			EsameDAOpostgres dao = new EsameDAOpostgres();
			Esame esame = new Esame();
			esame.setNome(nome.toLowerCase());
			esame.setDescrizione(descrizione.toLowerCase());
			esame.setCfu(cfu);
			return dao.insert(esame);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean eliminaEsame(Esame esame) {
		try {
			EsameDAOpostgres dao = new EsameDAOpostgres();
			return dao.delete(esame);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean aggiornaEsame(Esame esame, String nome, String descrizione, int cfu) {
		try {
			EsameDAOpostgres dao = new EsameDAOpostgres();
			return dao.update(esame, nome.toLowerCase(), descrizione.toLowerCase(), cfu);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Override
	public Esame trovaEsame(Long id) {
		try {
			EsameDAOpostgres dao = new EsameDAOpostgres();
			return dao.findById(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Esame> offertaFormativa() {
		try {
			EsameDAOpostgres dao = new EsameDAOpostgres();
			return dao.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Esame> trovaEsamePerNome(String nome) {
		try {
			EsameDAOpostgres dao = new EsameDAOpostgres();
			return dao.findByNome(nome.toLowerCase());
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Studente> mostraPdS() {
		try {
			PianoDiStudiDAOpostgres dao = new PianoDiStudiDAOpostgres();
			List<Integer> idstudenti = dao.findByAll();
			List<Studente> studenti = new ArrayList<Studente>();
			StudenteDAOpostgres sdao = new StudenteDAOpostgres();
			for(Integer matricola : idstudenti)
				studenti.add(sdao.findByMatricola(matricola));
			return studenti;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Studente> mostraStudentiConEsame(Esame esame) {
		try {
			PianoDiStudiDAOpostgres dao = new PianoDiStudiDAOpostgres();
			List<Integer> idstudenti = dao.findByEsame(esame);
			List<Studente> studenti = new ArrayList<Studente>();
			StudenteDAOpostgres sdao = new StudenteDAOpostgres();
			for(Integer matricola : idstudenti)
				studenti.add(sdao.findByMatricola(matricola));
			return studenti;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Esame> mostraEsamiPerStudente(int matricola) {
		try {
			PianoDiStudiDAOpostgres dao = new PianoDiStudiDAOpostgres();
			List<Long> idesame = dao.findByStudente(matricola);
			EsameDAOpostgres edao = new EsameDAOpostgres();
			List<Esame> esami = new LinkedList<Esame>();
			for(Long ide : idesame)
				esami.add(edao.findById(ide));
			return esami;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean inserisciEsamePerStudente(int studente, Long esame) {
		try {
			PianoDiStudiDAOpostgres dao = new PianoDiStudiDAOpostgres();
			return dao.insert(studente, esame);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminaEsamePerStudente(Studente studente, Esame esame) {
		try {
			PianoDiStudiDAOpostgres dao = new PianoDiStudiDAOpostgres();
			return dao.deleteEsameByStudente(studente, esame);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminaPdSPerStudente(int studente) {
		try {
			PianoDiStudiDAOpostgres dao = new PianoDiStudiDAOpostgres();
			return dao.deleteByStudente(studente);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean aggiornaPdS(Studente studente, Esame vecchio, Esame nuovo) {
		try {
			PianoDiStudiDAOpostgres dao = new PianoDiStudiDAOpostgres();
			return dao.update(studente, vecchio, nuovo);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}	

}