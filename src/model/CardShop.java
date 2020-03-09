package model;

import java.util.List;

import bean.ProductBean;
import dao.CardDAO;

public class CardShop {
	private CardDAO card;
	
	
	public CardShop() {
		try {
			card = new CardDAO();
		} catch (Exception e) {
			
		}
		
	}
	
	public List<ProductBean> retrieveCards(){
		return card.retrieveAll();
	}
	
	public ProductBean retrieveCard(String name) {
		return card.retrieve();
	}

}
