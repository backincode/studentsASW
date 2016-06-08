package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Facade;
import model.FacadeImp;
import model.Studente;

@Path("studenti")
public class Studenti {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getAll(){

        ArrayList<Map<String, String>> res = new ArrayList<>();

        Facade system=new FacadeImp();
        List<Studente> off=system.trovaStudentePerCognome("");
        if(off==null) return res;       //fail fast

        for(Studente e: off){
            HashMap<String, String> descr=new HashMap<>();
            descr.put("nome", e.getNome());
            descr.put("cognome", e.getCognome());
            descr.put("matricola", Integer.toString(e.getMatricola()));
            res.add(descr);
        }

        return res;
    }

    @GET
    @Path("{matricola}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getStudenteByMatricola(@PathParam("matricola") int matricola){
        HashMap<String, String> res = new HashMap<>();

        Facade system = new FacadeImp();
        Studente e=system.trovaStudentePerMatricola(matricola);
        if(e==null) return res;         //fail fast

        res.put("nome", e.getNome());
        res.put("cognome", e.getCognome());
        res.put("matricola",Integer.toString(e.getMatricola()));
        return res;
    }

    @GET
    @Path("nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap<String, String>> getStudenteByNome(@PathParam("nome") String nome){
        ArrayList<HashMap<String, String>> res = new ArrayList<>();

        Facade system = new FacadeImp();
        List<Studente> off=system.trovaStudentePerNome(nome);
        if(off==null) return res;         //fail fast

        for(Studente e: off){
            HashMap<String, String> descr=new HashMap<>();
            descr.put("nome", e.getNome());
            descr.put("matricola", Integer.toString(e.getMatricola()));
            descr.put("cognome", e.getCognome());
            res.add(descr);
        }
        return res;
    }

    @GET
    @Path("cognome/{cognome}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap<String, String>> getStudenteByCognome(@PathParam("cognome") String cognome){
        ArrayList<HashMap<String, String>> res = new ArrayList<>();

        Facade system = new FacadeImp();
        List<Studente> off=system.trovaStudentePerCognome(cognome);
        if(off==null) return res;         //fail fast

        for(Studente e: off){
            HashMap<String, String> descr=new HashMap<>();
            descr.put("nome", e.getNome());
            descr.put("matricola", Integer.toString(e.getMatricola()));
            descr.put("cognome", e.getCognome());
            res.add(descr);
        }
        return res;
    }
    
    @Path("inserisciStudente/")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String inserisciStudente(@FormParam("nome") String nome, @FormParam("cognome") String cognome, @FormParam("mat") int matricola) {
        Facade system = new FacadeImp();
        Boolean result = system.inserisciStudente(nome, cognome, matricola);
        if(result)
            return "studente inserito";
        else
         return "studente non inserito";
    }
}