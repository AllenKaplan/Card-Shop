package rest;

import model.CardModel;

import javax.ws.rs.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.ProductBean;

@Path("product") //this is the path of the resource
public class Product {

    @GET
    @Produces("text/json")
    public String getProduct(@QueryParam("productID") int id) throws Exception {
//    	return "hello world" + id;
    	ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(CardModel.getInstance().retrieveCardByID(id));
    }

}