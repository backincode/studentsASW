package action;

import javax.servlet.http.HttpServletRequest;

public class AzioneIndexEsami extends Azione{

	@Override
	public String esegui(HttpServletRequest request) {
		String risp= request.getParameter("scelta");
		if(risp.equals("Trova Esame"))
			return "trovaEsame";
		if(risp.equals("Inserisci Esame"))
			return "inserimentoEsame";
		return "errore";	
	}
}

