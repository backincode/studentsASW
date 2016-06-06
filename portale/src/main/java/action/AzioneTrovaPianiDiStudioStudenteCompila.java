package action;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Facade;
import model.FacadeImp;
import model.Studente;

public class AzioneTrovaPianiDiStudioStudenteCompila extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {

			try {
				HttpSession sessione = request.getSession();
				int matricola = Integer.valueOf(((String)request.getParameter("studente")));
				Set<Studente> studenti = new HashSet<Studente>();
				String esito = new String();
				Facade sistema = new FacadeImp();
				
				studenti.add(sistema.trovaStudentePerMatricola(matricola));
				studenti.addAll(sistema.trovaStudentePerNome(((String)request.getParameter("studente"))));
				studenti.addAll(sistema.trovaStudentePerCognome(((String)request.getParameter("studente"))));
				
				
				if(!studenti.isEmpty()) {
					for(Studente s : studenti)
						esito += "<tr><td><input class=\"blueButton\" type=\"submit\" name=\"compila\" value=\""+s.getMatricola()+"\" /></td><td>"
								+s.getNome()+"</td><td>"+s.getCognome()+"</td></tr>";
				}
				else
					esito = "Nessuno studente trovato";
				sessione.setAttribute("tipoEsito", esito);
				return "esitoPianoDiStudiStudentiCompila";
			} catch (NumberFormatException e){
				HttpSession sessione = request.getSession();
				Set<Studente> studenti = new HashSet<Studente>();
				String esito = new String();
				Facade sistema = new FacadeImp();
				studenti.addAll(sistema.trovaStudentePerNome(((String)request.getParameter("studente"))));
				studenti.addAll(sistema.trovaStudentePerCognome(((String)request.getParameter("studente"))));
				
				if(!studenti.isEmpty()) {
					for(Studente s : studenti)
						esito += "<tr><td><input class=\"blueButton\" type=\"submit\" name=\"compila\" value=\""+s.getMatricola()+"\" /></td><td>"
								+s.getNome()+"</td><td>"+s.getCognome()+"</td></tr>";
				}
				else
					esito = "Nessuno studente trovato";
				sessione.setAttribute("tipoEsito", esito);
				return "esitoPianoDiStudiStudentiCompila";
			}
		}

	}
