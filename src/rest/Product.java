package rest;

import model.CardModel;

import javax.ws.rs.*;

import bean.ProductBean;

@Path("/product") //this is the path of the resource
public class Product {

    @GET
    @Produces("text/plain")
    public String getProduct(@QueryParam("productID") int id) throws Exception {
    	return "hello world";
//        return CardModel.getInstance().retrieveCardByID(id);
    }

}