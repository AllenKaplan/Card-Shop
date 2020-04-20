package rest;

import model.CardModel;

import javax.ws.rs.*;

import bean.ProductBean;

@Path("product") //this is the path of the resource
public class Product {

    @GET
    @Path("/info/")
    @Produces("text/plain")
    public String getProduct(@QueryParam("productId") int id) throws Exception {
    	return "hello world" + id;
//        return CardModel.getInstance().retrieveCardByID(id);
    }

}