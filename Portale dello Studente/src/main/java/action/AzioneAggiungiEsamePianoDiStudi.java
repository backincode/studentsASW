package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Facade;
import model.FacadeImp;

public class AzioneAggiungiEsamePianoDiStudi extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		Long idesame = Long.valueOf(request.getParameter("esame"));
		HttpSession sessione = request.getSession();
		int matricola = (int)sessione.getAttribute("matricola");
		Facade sistema = new FacadeImp();
		if(sistema.inserisciEsamePerStudente(matricola, idesame)) 
			sessione.setAttribute("tipoEsito", "Inserimento effettuato correttamente.");
		else			
			sessione.setAttribute("tipoEsito", "Inserimento non effettuato.");
		return "esitoPianoDiStudi";
	}

}
