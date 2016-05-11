package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Esame;
import model.Facade;
import model.FacadeImp;


public class AzioneSceltaStudentePianoDiStudi extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		int matricola = Integer.valueOf(request.getParameter("compila"));
		System.out.println(matricola);
		HttpSession sessione = request.getSession();
		sessione.setAttribute("matricola", matricola);
		Facade sistema = new FacadeImp();
		sistema.eliminaPdSPerStudente(matricola);
		List<Esame> esami = sistema.offertaFormativa();
		String esito = new String();
		for(Esame e : esami) {
			esito += "<tr><td><input class=\"blueButton\" type=\"submit\" name=\"esame\" value=\""+e.getId()+"\" /></td><td>"
					+e.getNome()+"</td><td>"+e.getDescrizione()+"</td><td>"+e.getCfu()+"</td></tr>";
		}
		sessione.setAttribute("tipoEsitoPDS", esito);
		return "esitoPianoDiStudiEsami";
	}

}
