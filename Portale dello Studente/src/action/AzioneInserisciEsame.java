package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AzioneInserisciEsame extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		HttpSession sessione = request.getSession();
		sessione.setAttribute("nomeSessione", request.getParameter("nome"));
		sessione.setAttribute("descrizioneSessione", request.getParameter("descrizione"));
		sessione.setAttribute("cfuSessione", request.getParameter("cfu"));
		return "confermaInserimentoEsame";
	}

}
