package model;

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
    
    public void makePurchase(OrderBean order) throws Exception {
    	purchase.createOrder(order);
    }
}
