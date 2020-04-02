package bean;

public abstract class ProductBean {
	private String name;
	private String description;
	private double cost;
	private int limit;
	private String img;

	public ProductBean(String name, String description, double cost, int limit, String img) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.limit = limit;
		this.img = img;
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
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
