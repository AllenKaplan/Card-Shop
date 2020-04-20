package model;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.PurchaseHistoryBean;
import bean.UserPurchasesBean;
import bean.OrderBean;
import dao.PurchaseDAO;

public class PurchaseModel {
	private PurchaseDAO purchase;
	
    private static PurchaseModel instance;
	
    public static PurchaseModel getInstance() throws ClassNotFoundException{
        if (instance == null) {
            instance = new PurchaseModel();
            instance.purchase = new PurchaseDAO();
        }
        return instance;
    }
    
    private PurchaseModel() {}
	
	public ArrayList<PurchaseHistoryBean> getPurchasesByMonth(int month, int year) throws SQLException {
		
		String strMonth = (month < 10) ? "0" + Integer.toString(month) : Integer.toString(month);
		
		return purchase.getPurchasesByMonth(strMonth, Integer.toString(year));
	}
	
	public void makePurchase(OrderBean order) throws Exception {
    	purchase.createOrder(order);
    }
	
	public ArrayList<UserPurchasesBean> getPurchasesByUser() throws SQLException {
		return purchase.purchasesByUser();
	}
	
	public String getPurchasesByProductId(int id) throws SQLException {
		List<PurchaseHistoryBean> purchaseList = this.purchase.getPurchasesByProductId(id);
		

		ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(purchaseList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
		
		
		return jsonString;
	} 

}
