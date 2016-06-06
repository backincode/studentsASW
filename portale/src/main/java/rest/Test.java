package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by andrea on 01/06/16.
 */
@Path("supercalifragi")
public class Test {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getListiche(){
        ArrayList<String> res = new ArrayList<String>();
        Collections.addAll(res, "listi", "che", "spiralid", "HODOR");
        return res;
    }

    @Path("what")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getFunky(){
        List<Map<String, String>> res = new ArrayList<>();
        res.add(new HashMap<String, String>(){{put("winter", "is coming"); put("John Snow", "knows nothing");}
        });
        return res;
    }
}
