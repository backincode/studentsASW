package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Esame;
import model.Facade;
import model.FacadeImp;


public class AzioneSceltaStudentePianoDiStudiMostra extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		int matricola = Integer.valueOf(request.getParameter("mostra"));
		HttpSession sessione = request.getSession();
		sessione.setAttribute("matricola", matricola);
		Facade sistema = new FacadeImp();
		List<Esame> esami = sistema.mostraEsamiPerStudente(matricola);
		String esito = new String();
		for(Esame e : esami) {
			esito += "<tr><td>"+e.getId()+"</td><td>"
					+e.getNome()+"</td><td>"+e.getDescrizione()+"</td><td>"+e.getCfu()+"</td></tr>";
		}
		sessione.setAttribute("tipoEsito", esito);
		return "esitoPianoDiStudiEsamiMostra";
	}

}
