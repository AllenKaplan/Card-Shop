package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.PurchaseModel;;

@Path("order") //this is the path of the resource
public class Order {
	@GET
    @Path("/info/")
    @Produces("text/plain")
    public String getOrdersByProductId(@QueryParam("productId") int id) throws Exception {
    	
		try {
			return PurchaseModel.getInstance().getPurchasesByProductId(id);
		} catch (Exception e) {
			return e.toString();
		}
    }
}
