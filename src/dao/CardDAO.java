package dao;


import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CardBean;
import bean.ProductBean;

public class CardDAO {
	private DataSource dataSource;

	public CardDAO() throws ClassNotFoundException {
		try {
			dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ProductBean retrieve() {
		ProductBean testItem = new CardBean("testName", "testDesc", 420.69, 10, null);
		
		return testItem;
	}

	public List<ProductBean> retrieveAll() {
		List<ProductBean> products = new ArrayList<>();
		//Need to implement model for card shop; Currently testing with beans directly
		ProductBean testItem = new CardBean("testName", "testDesc", 420.69, 10, null);
		products.add(testItem);
		
		return products;
	}
	
}
