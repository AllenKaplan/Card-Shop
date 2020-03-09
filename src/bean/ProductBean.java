package bean;

import java.util.List;

public abstract class ProductBean {
	private String name;
	private String description;
	private double cost;
	private int rating;
	private List<String> images;

	public ProductBean(String name, String description, double cost, int rating, List<String> images) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.rating = rating;
		this.images = images;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	
}
