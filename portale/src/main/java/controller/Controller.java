package controller;

import action.Azione;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> comando2azione;
	private Map<String, String> esito2pagina;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doPost(request, response); // se la richiesta e' di tipo GET viene passata come POST
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prossimaPagina = null;
		String comando = this.leggiComando(request.getServletPath());
		String nomeAzione = this.comando2azione.get(comando);

		if (nomeAzione==null) {
			prossimaPagina = "/errore.jsp";
		}
		else {
			Azione azione = null;
			try {
				azione = (Azione)Class.forName(nomeAzione).newInstance();
				String esitoAzione = azione.esegui(request);
				prossimaPagina = this.esito2pagina.get(esitoAzione);
			} catch (InstantiationException e) {
				prossimaPagina = "/errore.jsp";
			} catch (IllegalAccessException e) {
				prossimaPagina = "/errore.jsp";
			} catch (ClassNotFoundException e) {
				prossimaPagina = "/errore.jsp";
			}
		}
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(prossimaPagina);
		rd.forward(request, response);

	}

	public void init() {
		/*COMANDI*/
		this.comando2azione = new HashMap<String, String>();
		this.comando2azione.put("index", "action.AzioneIndex");
		
		this.comando2azione.put("inserimentoStudente", "action.AzioneInserisciStudente");
		this.comando2azione.put("confermaInserimentoStudente", "action.AzioneConfermaInserimentoStudente");
		this.comando2azione.put("indexStudenti", "action.AzioneIndexStudenti");
		this.comando2azione.put("trovaStudente", "action.AzioneTrovaStudente");
		
		
		this.comando2azione.put("inserimentoEsame", "action.AzioneInserisciEsame");
		this.comando2azione.put("confermaInserimentoEsame", "action.AzioneConfermaInserimentoEsame");
		this.comando2azione.put("indexEsami", "action.AzioneIndexEsami");
		this.comando2azione.put("trovaEsame", "action.AzioneTrovaEsame");
		
		this.comando2azione.put("indexPianiDiStudio", "action.AzioneIndexPianiDiStudio");
		this.comando2azione.put("trovaPianoDiStudiStudenteCompila", "action.AzioneTrovaPianiDiStudioStudenteCompila");
		this.comando2azione.put("trovaPianoDiStudiStudenteMostra", "action.AzioneTrovaPianiDiStudioStudenteMostra");
		this.comando2azione.put("sceltaStudentePianoDiStudi", "action.AzioneSceltaStudentePianoDiStudi");
		this.comando2azione.put("aggiungiEsamePianoDiStudi", "action.AzioneAggiungiEsamePianoDiStudi");
		this.comando2azione.put("sceltaStudentePianoDiStudioMostra", "action.AzioneSceltaStudentePianoDiStudiMostra");

		
		/*ESITI*/
		this.esito2pagina = new HashMap<String, String>();
		this.esito2pagina.put("sezioneStudenti", "/indexStudenti.jsp");
		this.esito2pagina.put("inserimentoStudente", "/inserimentoStudente.jsp");
		this.esito2pagina.put("confermaInserimentoStudente", "/confermaInserimentoStudente.jsp");
		this.esito2pagina.put("trovaStudente", "/trovaStudente.jsp");
		
		this.esito2pagina.put("sezioneEsami", "/indexEsami.jsp");
		this.esito2pagina.put("inserimentoEsame", "/inserimentoEsame.jsp");
		this.esito2pagina.put("confermaInserimentoEsame", "/confermaInserimentoEsame.jsp");
		this.esito2pagina.put("trovaEsame", "/trovaEsame.jsp");

		this.esito2pagina.put("sezionePianiDiStudio", "/indexPianiDiStudi.jsp");
		this.esito2pagina.put("compilaPianoDiStudi", "/compilaPianoDiStudi.jsp");
		this.esito2pagina.put("mostraPianoDiStudi", "/mostraPianoDiStudi.jsp");
		this.esito2pagina.put("trovaPianoDiStudiStudenteCompila", "/compilaPianoDiStudi.jsp");
		this.esito2pagina.put("trovaPianoDiStudiStudenteMostra", "/mostraPianoDiStudio.jsp");
		
		
		this.esito2pagina.put("errore","/errore.jsp");
		this.esito2pagina.put("esitoStudenti","/esitoStudenti.jsp");
		this.esito2pagina.put("esitoEsami","/esitoEsami.jsp");
		this.esito2pagina.put("esitoPianoDiStudiStudentiCompila","/esitoPianoDiStudiStudentiCompila.jsp");
		this.esito2pagina.put("esitoPianoDiStudioStudentiMostra","/esitoPianoDiStudioStudentiMostra.jsp");
		this.esito2pagina.put("esitoPianoDiStudiEsami","/esitoPianoDiStudiEsami.jsp");
		this.esito2pagina.put("esitoPianoDiStudi","/esitoPianoDiStudi.jsp");
		this.esito2pagina.put("esitoPianoDiStudiEsamiMostra","/esitoPianoDiStudioMostra.jsp");
		this.esito2pagina.put("esito","/esito.jsp");

	}

	/*Restituisce la servletPath senza il "/" e il ".do"*/
	private String leggiComando(String servletPath) {
		StringBuffer str = new StringBuffer(servletPath);
		return str.substring(1,str.lastIndexOf(".do"));
	}

}
