package action;

import javax.servlet.http.HttpServletRequest;

public class AzioneIndex extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		String risp= request.getParameter("scelta");
		if(risp.equals("Offerta Formativa"))
			return "offertaFormativa";
		if(risp.equals("Sezione Studenti"))
			return "sezioneStudenti";
		if(risp.equals("Sezione Esami"))
			return "sezioneEsami";
		if(risp.equals("Sezione Piani di Studio"))
			return "sezionePianiDiStudio";
		return "errore";	}

}
