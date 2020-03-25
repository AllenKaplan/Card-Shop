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
		//Need to implement model for card shop; Currently testing with beans directly=
		
		ProductBean testItem1 = new CardBean("testName1", "testDesc1", 420.69, 1, null);
		ProductBean testItem2 = new CardBean("testName2", "testDesc2", 420.69, 2, null);
		ProductBean testItem3 = new CardBean("testName3", "testDesc3", 420.69, 3, null);
		products.add(testItem1);
		products.add(testItem2);
		products.add(testItem3);
		
		return products;
	}
	
}
