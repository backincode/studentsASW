package persistence.postgres;

import java.util.ArrayList;
import java.util.List;

import model.Studente;
import persistence.PersistenceException;
import model.Esame;

public class StudenteProxy extends Studente {
	private boolean caricato = false; // caching

	public List<Esame> getEsami() {
		// Se ri-uso lo stesso oggetto Studente non viene fatta 2 volte la richiesta
		// al db risparmiandone il costo
		if (!this.caricato) {
			List<Long> idesami = new ArrayList<Long>();
			List<Esame> esami = new ArrayList<Esame>();
			PianoDiStudiDAOpostgres pdao = new PianoDiStudiDAOpostgres();
			try {
				idesami = pdao.findByStudente(this.getMatricola());
				EsameDAOpostgres edao = new EsameDAOpostgres();
				for(Long e : idesami) {
					esami.add(edao.findById(e));
				}
					
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
			this.setEsami(esami);
			this.caricato = true;
		}
		return super.getEsami();
	}
}
