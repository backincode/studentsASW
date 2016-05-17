package action;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Esame;
import model.Facade;
import model.FacadeImp;

public class AzioneTrovaEsame extends Azione{

	@Override
	public String esegui(HttpServletRequest request) {
		HttpSession sessione = request.getSession();

		List<Esame> esami = new LinkedList<Esame>();
		String esito = new String();
		Facade sistema = new FacadeImp();
		
		esami.addAll(sistema.trovaEsamePerNome(((String)request.getParameter("esame"))));


		if(!esami.isEmpty()) {
			for(Esame s : esami)
				esito += s.toString()+"\n";
		}
		else
			esito = "Nessun esame trovato";
		sessione.setAttribute("tipoEsito", esito);
		return "esitoEsami";
	}

}