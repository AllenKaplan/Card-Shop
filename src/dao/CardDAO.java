package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CardBean;
import bean.ProductBean;

public class CardDAO {
	private DataSource dataSource;

	public CardDAO() throws ClassNotFoundException {
		try {
			dataSource = (DataSource) (new InitialContext()).lookup(DatabaseAccess.instance.ACTIVE_ACCESS);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ProductBean retrieve() {
		/*
		select * from cards where id=id_number
		*/
		ProductBean testItem = new CardBean("testName", "testDesc", 420.69, 10, null);
		
		return testItem;
	}

	public List<ProductBean> retrieveAll() throws SQLException{
		List<ProductBean> products = new ArrayList<>();
		
		String query = "select * from cards join cardmarket on cards.number = cardmarket.number";      
		Connection con = this.dataSource.getConnection();   
		PreparedStatement p = con.prepareStatement(query);   
		ResultSet r = p.executeQuery();   
		while (r.next())
		{    
			String name = r.getString("name");
			String desc = r.getString("description");
			Double price = Double.parseDouble(r.getString("sellingPrice"));
			Integer rating = Integer.parseInt(r.getString("Limit"));				
			products.add(new CardBean(name, desc, price, rating, null));
		}   
		r.close();   
		p.close();   
		con.close();   
		return products;     
		
		
		/*
		//Need to implement model for card shop; Currently testing with beans directly
		ProductBean testItem1 = new CardBean("testName1", "testDesc1", 420.69, 1, null);
		ProductBean testItem2 = new CardBean("testName2", "testDesc2", 420.69, 2, null);
		ProductBean testItem3 = new CardBean("testName3", "testDesc3", 420.69, 3, null);
		products.add(testItem1);
		products.add(testItem2);
		products.add(testItem3);
		*/
	}
	
}
