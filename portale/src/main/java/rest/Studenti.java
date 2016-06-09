package rest;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import model.Facade;
import model.FacadeImp;
import model.Studente;

@Path("studenti")
public class Studenti {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Studente> getAll(){
        try {
            List<Studente> res = new ArrayList<>();
            Facade system = new FacadeImp();
            List<Studente> off = system.trovaStudentePerCognome("");
            if (off == null)
                return res;       //fail fast

            res = off;
            return res;
        }
        catch (Exception ex){
            String errorMessage = "Error while finding all elements in Studenti: " + ex.getMessage();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }
    }

    @GET
    @Path("{matricola}")
    @Produces(MediaType.APPLICATION_JSON)
    public Studente getStudenteByMatricola(@PathParam("matricola") int matricola){
        try {
            Facade system = new FacadeImp();
            Studente e = system.trovaStudentePerMatricola(matricola);
            if (e == null)
                throw new WebApplicationException(Response.Status.NOT_FOUND);         //fail fast
            return e;
        }
        catch (Exception ex) {
            String errorMessage = "Error while finding Studente with matricola" + matricola + ": " + ex.getMessage();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }
    }

    @GET
    @Path("nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Studente> getStudentiByNome(@PathParam("nome") String nome){
        try{
            List<Studente> res;
            Facade system = new FacadeImp();
            List<Studente> off=system.trovaStudentePerNome(nome);
            if(off==null)
                throw new WebApplicationException(Response.Status.NOT_FOUND);         //fail fast
            res=off;
            return res;
        }
        catch (Exception ex){
            String errorMessage = "Error while finding Studente with nome" + nome + ": " + ex.getMessage();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }
    }

    @Path("inserisciStudente/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserisciStudente(@FormParam("nome") String nome, @FormParam("matricola") int matricola, @FormParam("cognome") String cognome) {
        Facade system = new FacadeImp();
        Boolean result = system.inserisciStudente(nome, cognome, matricola);
        if(result){
            List<Studente> list = system.trovaStudentePerNome(nome);
            try {
                for (Studente e : list) {
                    if (e.getMatricola() == matricola)
                        return Response.created(URI.create("/" + e.getMatricola())).entity(e).build();
                }
                return Response.ok().build();
            }
            catch (Exception ex){
                String errorMessage = "Error while creating Studente with nome " + nome + ", cognome "+ cognome + " and matricola "+ matricola +" : " + ex.getMessage();
                throw new WebApplicationException(
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(errorMessage).type("text/plain").build());
            }
        }
        else{
            String errorMessage = "Error while creating Studente with nome " + nome + ", cognome "+ cognome + " and matricola "+ matricola +
                    ": are you using the same id?";
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }
    }

    @Path("cancellaStudente/{matricola}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudente(@PathParam("matricola") int matricola){
        try {
            Facade system = new FacadeImp();
            Studente studente = system.trovaStudentePerMatricola(matricola);

            if (studente==null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                boolean res = system.eliminaStudente(studente);
                if(res)
                    return Response.ok(studente).status(Response.Status.OK).build();
                else {
                    String errorMessage = "Error while deleting Studente with matricola " + matricola + ": is still referenced in any record in PianoDiStudi table?";
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build();
                }
            }
        } catch (Exception e) {
            String errorMessage = "Error while deleting Studente with matricola: " + matricola + ": " + e.getMessage();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }
    }

    @Path("aggiornaStudente/{matricola}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response aggiornaStudente(@PathParam("matricola") int matricola, @FormParam("nome") String nome, @FormParam("cognome") String cognome) {
        Facade system = new FacadeImp();
        Studente studente = system.trovaStudentePerMatricola(matricola);
        if(studente==null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        else {
            try{
                boolean result = system.aggiornaStudente(studente,nome,cognome);
                if (result) {
                    Studente tmp = new Studente();
                    tmp.setNome(nome);
                    tmp.setCognome(cognome);
                    tmp.setMatricola(matricola);
                    tmp.setEsami(system.trovaStudentePerMatricola(matricola).getEsami());
                    return Response.ok(tmp).status(Response.Status.OK).build();
                }
                else {
                    String errorMessage = "Error while updating Studente " + studente.toString();
                    throw new WebApplicationException(
                            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                    .entity(errorMessage).type("text/plain").build());
                }
            }
            catch (Exception ex){
                String errorMessage = "Error while updating Studente with matricola " + matricola +" : "+ ex.getMessage();
                throw new WebApplicationException(
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(errorMessage).type("text/plain").build());
            }
        }
    }
}