package model;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseHistoryBean;
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

}
