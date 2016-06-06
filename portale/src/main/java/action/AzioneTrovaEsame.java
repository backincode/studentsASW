package action;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Esame;
import model.Facade;
import model.FacadeImp;

public class AzioneTrovaEsame extends Azione{

	@Override
	public String esegui(HttpServletRequest request) {
		HttpSession sessione = request.getSession();

		Set<Esame> esami = new HashSet<Esame>();
		String esito = new String();
		Facade sistema = new FacadeImp();

		try {
			Esame tmp = sistema.trovaEsame(Long.valueOf(request.getParameter("esame")));
			if(tmp!=null)
				esami.add(tmp);
			esami.addAll(sistema.trovaEsamePerNome(((String)request.getParameter("esame"))));



			if(!esami.isEmpty()) {
				for(Esame s : esami)
					esito += s.toString()+"\n";
			}
			else
				esito = "Nessun esame trovato";
			sessione.setAttribute("tipoEsito", esito);
			return "esitoEsami";
		} catch(NumberFormatException e) {
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

}