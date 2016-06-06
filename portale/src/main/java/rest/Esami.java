package rest;

import model.Esame;
import model.Facade;
import model.FacadeImp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public Map<String, String> getDescr(@PathParam("{id}") long id){
        HashMap<String, String> res = new HashMap<>();

        Facade system = new FacadeImp();
        Esame e=system.trovaEsame(id);
        if(e==null) return res;         //fail fast

        res.put("nome", e.getNome());
        res.put("descrizione", e.getDescrizione());
        res.put("cfu",Integer.toString(e.getCfu()));
        res.put("id", Long.toString(e.getId()));
        return res;
    }
}
