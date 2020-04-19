package bean;

import java.util.List;

public class CardBean extends ProductBean {
	//TODO add enum attributes for the card's class (SS, S, A) and specified slots.
	
	public CardBean(int id, String name, String description, double cost, int rating, String img) {
		super(id, name, description, cost, rating, img);
	}
	
	
}
