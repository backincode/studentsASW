package model;

import java.util.List;

public interface Facade {
	/*STUDENTE*/
	public boolean inserisciStudente(String nome, String cognome, int matricola);
	public boolean eliminaStudente(Studente studente);
	public boolean aggiornaStudente(Studente studente, String nome, String cognome);
	public Studente trovaStudentePerMatricola(int matricola);
	public List<Studente> trovaStudentePerNome(String nome);
	public List<Studente> trovaStudentePerCognome(String cognome);


	/*ESAME*/
	public boolean inserisciEsame(String nome, String descrizione, int cfu);
	public boolean eliminaEsame(Esame esame);
	public boolean aggiornaEsame(Esame esame, String nome, String descrizione, int cfu);
	public Esame trovaEsame(Long id);
	public List<Esame> trovaEsamePerNome(String nome);
	public List<Esame> offertaFormativa();
	
	/*PIANO DI STUDI*/
	public List<Studente> mostraPdS();
	public List<Esame> mostraEsamiPerStudente(int matricola);
	public List<Studente> mostraStudentiConEsame(Esame esame);
	public boolean inserisciEsamePerStudente(int studente, Long esame);
	public boolean eliminaEsamePerStudente(Studente studente, Esame esame);
	public boolean eliminaPdSPerStudente(int studente);
	public boolean aggiornaPdS(Studente studente, Esame vecchio, Esame nuovo);

	

}
