package action;

import javax.servlet.http.HttpServletRequest;

public class AzioneIndexPianiDiStudio extends Azione {

	@Override
	public String esegui(HttpServletRequest request) {
		String risp= request.getParameter("scelta");
		if(risp.equals("Compila Piano di Studio"))
			return "trovaPianoDiStudiStudenteCompila";
		if(risp.equals("Mostra Piano di Studio"))
			return "trovaPianoDiStudiStudenteMostra";
		return "errore";	
	}

}
