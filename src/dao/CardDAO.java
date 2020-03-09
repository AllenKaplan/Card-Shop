package dao;

import java.util.ArrayList;
import java.util.List;

import bean.CardBean;
import bean.ProductBean;

public class CardDAO {

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
