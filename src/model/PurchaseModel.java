package model;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.PurchaseHistoryBean;
import dao.PurchaseDAO;

public class PurchaseModel {
	
	private PurchaseDAO purchase;
	
	public PurchaseModel() {
		try {
			purchase = new PurchaseDAO();
		} catch (Exception e) {
			
		}
	}
	
	public ArrayList<PurchaseHistoryBean> getPurchasesByMonth(int month, int year) throws SQLException {
		
		String strMonth = (month < 10) ? "0" + Integer.toString(month) : Integer.toString(month);
		
		return purchase.getPurchasesByMonth(strMonth, Integer.toString(year));
	}

}
