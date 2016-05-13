package helper;

import javax.servlet.http.HttpServletRequest;

public class FormHelperStudente implements FormHelper {
	private String nome;
	private String cognome;
	private String matricola;
	private HttpServletRequest richiesta;

	public FormHelperStudente(HttpServletRequest request) {
		this.nome = request.getParameter("nome");
		this.cognome = request.getParameter("cognome");
		this.matricola = request.getParameter("matricola");
		this.richiesta = request;
	}

	@Override
	public boolean convalida() {
		boolean convalida = true;
		if(this.nome==null||this.nome.isEmpty())	{
			this.richiesta.setAttribute("msgerroreNome", "Nome non inserito correttamente");
			convalida = false;
		}
		if(this.cognome==null||this.cognome.isEmpty())	{
			this.richiesta.setAttribute("msgerroreCognome", "Cognome non inserita correttamente");
			convalida = false;
		}
		if(this.matricola==null||!ControlloStringa.seMatricola(this.matricola))	{
			this.richiesta.setAttribute("msgerroreMatricola", "Matricola non inserito correttamente");
			convalida = false;
		}
		return convalida;
	}

}
