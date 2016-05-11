package action;

import javax.servlet.http.HttpServletRequest;

public class AzioneIndexStudenti extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		String risp= request.getParameter("scelta");
		if(risp.equals("Trova Studente"))
			return "trovaStudente";
		if(risp.equals("Inserisci Studente"))
			return "inserimentoStudente";
		return "errore";	
		}

}
