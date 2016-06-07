package rest;

import model.Esame;
import model.Facade;
import model.FacadeImp;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andrea on 04/06/16.
 */
@Path("esami")
public class Esami {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getAll(){

        ArrayList<Map<String, String>> res = new ArrayList<>();

        Facade system=new FacadeImp();
        List<Esame> off=system.offertaFormativa();
        if(off==null) return res;       //fail fast

        for(Esame e: off){
            HashMap<String, String> descr=new HashMap<>();
            descr.put("nome", e.getNome());
            descr.put("cfu", Integer.toString(e.getCfu()));
            descr.put("id", Long.toString(e.getId()));
            res.add(descr);
        }

        return res;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getEsameById(@PathParam("id") long id){
        HashMap<String, String> res = new HashMap<String,String>();

        Facade system = new FacadeImp();
        Esame e=system.trovaEsame(id);
        if(e==null) return res;         //fail fast

        res.put("nome", e.getNome());
        res.put("descrizione", e.getDescrizione());
        res.put("cfu",Integer.toString(e.getCfu()));
        res.put("id", Long.toString(e.getId()));
        return res;
    }

    @GET
    @Path("nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Map<String, String>> getEsameByNome(@PathParam("nome") String nome){
        ArrayList<Map<String, String>> res = new ArrayList<>();

        Facade system = new FacadeImp();
        List<Esame> off=system.trovaEsamePerNome(nome);
        if(off==null) return res;         //fail fast

        for(Esame e: off){
            HashMap<String, String> descr=new HashMap<>();
            descr.put("nome", e.getNome());
            descr.put("cfu", Integer.toString(e.getCfu()));
            descr.put("id", Long.toString(e.getId()));
            res.add(descr);
        }
        return res;
    }

    @Path("inserisciEsame/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String inserisciEsame(@FormParam("nome") String nome, @FormParam("cfu") int cfu, @FormParam("descr") String descrizione) {
        Facade system = new FacadeImp();
        Boolean result = system.inserisciEsame(nome, descrizione, cfu);
        if(result)
            return "esame inserito";
        else
         return "esame non inserito";
    }
}