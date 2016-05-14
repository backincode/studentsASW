package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import helper.FormHelperEsame;

public class AzioneInserisciEsame extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		HttpSession sessione = request.getSession();
		FormHelperEsame ch = new FormHelperEsame(request);
		if(!ch.convalida())
			return "inserimentoEsame";
		sessione.setAttribute("nomeEsameSessione", request.getParameter("nome"));
		sessione.setAttribute("descrizioneSessione", request.getParameter("descrizione"));
		sessione.setAttribute("cfuSessione", request.getParameter("cfu"));
		return "confermaInserimentoEsame";
	}

}
