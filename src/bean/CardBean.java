package bean;

import java.util.List;

public class CardBean extends ProductBean {
	//TODO add enum attributes for the card's class (SS, S, A) and specified slots.
	
	public CardBean(String name, String description, double cost, int rating, List<String> images) {
		super(name, description, cost, rating, images);
	}
}
