package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import helper.FormHelperStudente;

public class AzioneInserisciStudente extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		HttpSession sessione = request.getSession();
		FormHelperStudente ch = new FormHelperStudente(request);
		if(!ch.convalida())
			return "inserimentoStudente";
		sessione.setAttribute("nomeSessione", request.getParameter("nome"));
		sessione.setAttribute("cognomeSessione", request.getParameter("cognome"));
		sessione.setAttribute("matricolaSessione", request.getParameter("matricola"));
		return "confermaInserimentoStudente";
	}

}
