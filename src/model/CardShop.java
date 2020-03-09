package model;

import java.util.List;

import bean.ProductBean;
import dao.CardDAO;

public class CardShop {
	private CardDAO card = new CardDAO();
	
	
	public CardShop() {
	}
	
	public List<ProductBean> retrieveCards(){
		return card.retrieveAll();
	}
	
	public ProductBean retrieveCard(String name) {
		return card.retrieve();
	}

}
