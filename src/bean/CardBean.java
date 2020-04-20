package bean;

import java.util.List;

public class CardBean extends ProductBean {
	private String rank; 
	private String spellType; 
	private int stock; 
	private double price; 
	private String classType; 

	public CardBean(int number, String name, String description, String rank, int limit, String classType, String spellType, String image, int stock, double price)
	{
		super(number, name, description, price, limit, image);
		this.rank = rank;
		this.spellType = spellType; 
		this.price = price;
		this.stock = stock;

	}	
	
	public String getClassType() {
		return classType;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRank() {
		return rank;
	}

	public String getSpellType() {
		return spellType;
	}
	
	
}
