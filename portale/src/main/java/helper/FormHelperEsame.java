package helper;

import javax.servlet.http.HttpServletRequest;

public class FormHelperEsame implements FormHelper {
	private String nome;
	private String descrizione;
	private String cfu;
	private HttpServletRequest richiesta;

	public FormHelperEsame(HttpServletRequest request) {
		this.nome = request.getParameter("nome");
		this.descrizione = request.getParameter("descrizione");
		this.cfu = request.getParameter("cfu");
		this.richiesta = request;
	}

	@Override
	public boolean convalida() {
		boolean convalida = true;
		if(this.nome==null||this.nome.isEmpty())	{
			this.richiesta.setAttribute("msgerroreNome", "Nome non inserito correttamente");
			convalida = false;
		}
		if(this.descrizione==null||this.descrizione.isEmpty())	{
			this.richiesta.setAttribute("msgerroreDescrizione", "Descrizione non inserita correttamente");
			convalida = false;
		}
		if(this.cfu==null||!ControlloStringa.seCfu(this.cfu))	{
			this.richiesta.setAttribute("msgerroreCfu", "CFU non inserito correttamente");
			convalida = false;
		}
		return convalida;
	}

}
