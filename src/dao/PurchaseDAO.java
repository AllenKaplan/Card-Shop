package dao;

import bean.PurchaseHistoryBean;
import bean.UserPurchasesBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PurchaseDAO {
	private DataSource dataSource;

	public PurchaseDAO() throws ClassNotFoundException {
		try {
			dataSource = (DataSource) (new InitialContext()).lookup(DatabaseAccess.instance.ACTIVE_ACCESS);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get purchases on a particular month of the year
	 * @param month integer (as String) from 01-12 (i.e. 05 is May)
	 * @param year integer (as String) from 0001-9999 (i.e. 2020 of year 2020)
	 * @return ArrayList of all purchases made this month grouped by card number.
	 * @throws SQLException 
	 */
	public ArrayList<PurchaseHistoryBean> getPurchasesByMonth(String month, String year) throws SQLException
	{
		String date = year + "-" + month + "-%";
		String ordersQuery = "select itemNumber, itemName, salePrice, sum(quantity)purchases from orders where datePurchased like ? group by itemNumber, itemName, salePrice";
		
		Connection con = this.dataSource.getConnection();   
		PreparedStatement orders = con.prepareStatement(ordersQuery);   
		orders.setString(1, date);	
		ResultSet ordersResult = orders.executeQuery();  	
		
		ArrayList<PurchaseHistoryBean> purchases = new ArrayList<PurchaseHistoryBean>();
		while (ordersResult.next())
		{
			int number = ordersResult.getInt("itemNumber");
			String name = ordersResult.getString("itemName");
			double price = ordersResult.getInt("salePrice");
			int quantity = ordersResult.getInt("purchases");
			purchases.add(new PurchaseHistoryBean(number,name,price,quantity,null)); //null image
		}
		orders.close();
		ordersResult.close();
		con.close();
		return purchases;		
	}
	
	public ArrayList<UserPurchasesBean> purchasesByUser() throws SQLException{
		String query = "select username, zip, sum(salePrice*quantity)totalSpent from orders join addresses on orders.deliveryAddress = addresses.addressID group by username, zip";
		
		Connection con = this.dataSource.getConnection();   
		PreparedStatement statement = con.prepareStatement(query);   
		ResultSet results = statement.executeQuery();  	
		ArrayList<UserPurchasesBean> users = new ArrayList<UserPurchasesBean>();
		
		while (results.next())
		{
			users.add(new UserPurchasesBean(results.getString("zip"),results.getDouble("totalSpent")));
		}
		statement.close();
		results.close();
		con.close();
		return users;
	
	}
	
	

}
