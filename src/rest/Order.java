package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("order") //this is the path of the resource
public class Order {
	@GET
    @Path("/info/")
    @Produces("text/plain")
    public String getOrdersByProductId(@QueryParam("productId") int id) throws Exception {
    	return "hello world" + id;
    }
}
