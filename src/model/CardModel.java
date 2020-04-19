package model;

import java.sql.SQLException;
import java.util.List;

import bean.ProductBean;
import dao.CardDAO;

public class CardModel {
	private CardDAO card;
	
	
	public CardModel() {
		try {
			card = new CardDAO();
		} catch (Exception e) {
			
		}
		
	}
	
	public List<ProductBean> retrieveCards() throws SQLException{
		return card.retrieveAll();
	}
	
	public ProductBean retrieveCard(String name) {
		return card.retrieve();
	}
	

	public List<ProductBean> search(String searchQuery) throws SQLException{
		return card.search(searchQuery);
	}

}
