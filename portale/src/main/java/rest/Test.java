package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by andrea on 01/06/16.
 */
@Path("supercalifragi")
public class Test {

    @GET
    @Produces("text/plain")
    public String getListiche(){
        return "spiralidoso";
    }
}
