package bean;

public abstract class ProductBean {
	private int id;
	private String name;
	private String description;
	private double cost;
	private int limit;
	private String img;

	public ProductBean(int id, String name, String description, double cost, int limit, String img) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.limit = limit;
		this.img = img;
	}
		
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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
