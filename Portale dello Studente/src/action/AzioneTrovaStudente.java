package action;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Facade;
import model.FacadeImp;
import model.Studente;

public class AzioneTrovaStudente extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		try {
			HttpSession sessione = request.getSession();
			
			int matricola = Integer.valueOf(((String)request.getParameter("studente")));
			List<Studente> studenti = new LinkedList<Studente>();
			String esito = new String();
			Facade sistema = new FacadeImp();
			
			Studente s = sistema.trovaStudentePerMatricola(matricola);
			if(s!=null)
				studenti.add(s);
			List<Studente> ls = sistema.trovaStudentePerNome(((String)request.getParameter("studente")));
			if(!ls.isEmpty())
				studenti.addAll(ls);
			ls = sistema.trovaStudentePerCognome(((String)request.getParameter("studente")));
			if(!ls.isEmpty())
				studenti.addAll(ls);
			
			
			if(!studenti.isEmpty()) {
				for(Studente i : studenti)
					esito += i.toString()+"\n";
			}
			else
				esito = "Nessuno studente trovato";
			sessione.setAttribute("tipoEsito", esito);
			return "esitoStudenti";
		} catch (NumberFormatException e){
			HttpSession sessione = request.getSession();
			List<Studente> studenti = new LinkedList<Studente>();
			String esito = new String();
			Facade sistema = new FacadeImp();
			studenti.addAll(sistema.trovaStudentePerNome(((String)request.getParameter("studente"))));
			studenti.addAll(sistema.trovaStudentePerCognome(((String)request.getParameter("studente"))));
			
			
			if(!studenti.isEmpty()) {
				for(Studente s : studenti)
					esito += s.toString()+"\n";
			}
			else
				esito = "Nessuno studente trovato";
			sessione.setAttribute("tipoEsito", esito);
			return "esitoStudenti";
		}
	}

}
