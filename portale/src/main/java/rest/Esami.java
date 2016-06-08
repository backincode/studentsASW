package rest;

import model.Esame;
import model.Facade;
import model.FacadeImp;

import java.net.URI;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 04/06/16.
 */
@Path("esami")
public class Esami {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Esame> getAll(){
        try {
            List<Esame> res = new ArrayList<>();
            Facade system = new FacadeImp();
            List<Esame> off = system.offertaFormativa();
            if (off == null)
                return res;       //fail fast

            res = off;
            return res;
        }
        catch (Exception ex){
            String errorMessage = "Error while finding all elements in Esami: " + ex.getMessage();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Esame getEsameById(@PathParam("id") long id){

        try {
            Facade system = new FacadeImp();
            Esame e = system.trovaEsame(id);
            if (e == null)
                throw new WebApplicationException(Response.Status.NOT_FOUND);         //fail fast
            return e;
        }
            catch (Exception ex) {
                String errorMessage = "Error while finding Esame with id" + id + ": " + ex.getMessage();
                throw new WebApplicationException(
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(errorMessage).type("text/plain").build());
            }
    }

    @GET
    @Path("nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Esame> getEsameByNome(@PathParam("nome") String nome){
        try{
            List<Esame> res;
            Facade system = new FacadeImp();
            List<Esame> off=system.trovaEsamePerNome(nome);
            if(off==null)
                throw new WebApplicationException(Response.Status.NOT_FOUND);         //fail fast
            res=off;
            return res;
        }
        catch (Exception ex){
            String errorMessage = "Error while finding Esame with nome" + nome + ": " + ex.getMessage();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }
    }

    @Path("inserisciEsame/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserisciEsame(@FormParam("nome") String nome, @FormParam("cfu") int cfu, @FormParam("descr") String descrizione) {
            Facade system = new FacadeImp();
            Boolean result = system.inserisciEsame(nome, descrizione, cfu);
            if(result){
                List<Esame> list = system.trovaEsamePerNome(nome);
                try {
                    for (Esame e : list) {
                        if (e.getCfu() == cfu)
                            return Response.created(URI.create("/" + e.getId())).entity(e).build();
                    }
                    return Response.ok().build();
                }
                catch (Exception ex){
                    String errorMessage = "Error while creating Esame with name " + nome + ", CFU "+ cfu + " and description \""+ descrizione +"\" : " + ex.getMessage();
                    throw new WebApplicationException(
                            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                    .entity(errorMessage).type("text/plain").build());
                }
            }
            else{
                String errorMessage = "Error while creating Esame with name " + nome + ", CFU "+ cfu + " and description \""+ descrizione +"\"" +
                        ": are you using the same id?";
                throw new WebApplicationException(
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(errorMessage).type("text/plain").build());

        }
    }

    @Path("cancellaEsame/{id}")
    @DELETE
    public Response deleteEsame(@PathParam("id") Long id){
        try {
            Facade system = new FacadeImp();
            Esame esame = system.trovaEsame(id);

            if (esame==null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                boolean res = system.eliminaEsame(esame);
                if(res)
                    return Response.ok(esame).status(Response.Status.OK).build();
                else {
                    String errorMessage = "Error while deleting Esame with id: " + id;
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build();
                }
            }
        } catch (Exception e) {
            String errorMessage = "Error while deleting Esame with id: " + id + ": " + e.getMessage();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(errorMessage).type("text/plain").build());
        }

    }

    @Path("aggiornaEsame/")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response aggiornaEsame(@FormParam("nome") String nome, @FormParam("cfu") int cfu, @FormParam("descr") String descrizione) {
        Facade system = new FacadeImp();

        List<Esame> res = system.trovaEsamePerNome(nome);
        if(res.isEmpty())
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        else {
            try{
                Esame esame = res.get(0);
                boolean result = system.aggiornaEsame(esame,nome,descrizione,cfu);
                if (result)
                    return Response.ok(esame).status(Response.Status.OK).build();
                else {
                    String errorMessage = "Error while updating Esame " + esame.toString();
                    throw new WebApplicationException(
                            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                    .entity(errorMessage).type("text/plain").build());
                }

            }
            catch (Exception ex){
                String errorMessage = "Error while updating Esame with name " + nome + ", CFU "+ cfu + " and description \""+ descrizione +"\" : "+ ex.getMessage();
                throw new WebApplicationException(
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(errorMessage).type("text/plain").build());
            }
        }


    }
}