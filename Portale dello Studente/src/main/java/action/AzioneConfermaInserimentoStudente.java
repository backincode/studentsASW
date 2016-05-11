package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Facade;
import model.FacadeImp;

public class AzioneConfermaInserimentoStudente extends Azione{

	@Override
	public String esegui(HttpServletRequest request) {

				String risp= request.getParameter("risposta");
				Facade sistema = new FacadeImp();
				HttpSession sessione = request.getSession();

				if(risp.equals("si"))	{
					if(sistema.inserisciStudente((String)sessione.getAttribute("nomeSessione"), (String)sessione.getAttribute("cognomeSessione"), Integer.valueOf((String)sessione.getAttribute("matricolaSessione"))))	{
						sessione.setAttribute("tipoEsito", "Inserimento effettuato correttamente.");
						return "esito";
					}
					else	{
						sessione.setAttribute("tipoErrore", "Inserimento non effettuato.");
						return "errore";
					}
				}
				return "inserimentoStudente";
			}



}
