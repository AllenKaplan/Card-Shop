package bean;

/**
 * Stores information about a purchase. Each bean holds information about one card item being purchased.
 * Multiple beans required for purchases of more than 1 card type
 */
public class OrderBean {
	private String username;
	private int itemNumber;
	private String itemName;
	private double salePrice; 
	private int quantity;
	private int deliveryAddress;
	private String datePurchased;
	private String dateDelievered;
	private String status; 
	
	/**
	 * Used when order being placed. Status is initialized, and delivery has not yet been made
	 * @param username
	 * @param itemNumber
	 * @param itemName
	 * @param salePrice
	 * @param quantity
	 * @param deliveryAddress
	 * @param datePurchased
	 */
	public OrderBean(String username, int itemNumber, String itemName, double salePrice, int quantity,
			int deliveryAddress, String datePurchased) {
		this.username = username;
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.salePrice = salePrice;
		this.quantity = quantity;
		this.deliveryAddress = deliveryAddress;
		this.datePurchased = datePurchased;
		this.dateDelievered = null;
		this.status = "PROCESSING";
	}
	
	/**
	 * used when order already placed and retrieving all info
	 * @param username
	 * @param itemNumber
	 * @param itemName
	 * @param salePrice
	 * @param quantity
	 * @param deliveryAddress
	 * @param datePurchased
	 * @param dateDelievered
	 * @param status
	 */
	public OrderBean(String username, int itemNumber, String itemName, double salePrice, int quantity,
			int deliveryAddress, String datePurchased, String dateDelievered, String status) {
		this.username = username;
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.salePrice = salePrice;
		this.quantity = quantity;
		this.deliveryAddress = deliveryAddress;
		this.datePurchased = datePurchased;
		this.dateDelievered = dateDelievered;
		this.status = status;
	}

	public String getDateDelievered() {
		return dateDelievered;
	}

	public void setDateDelievered(String dateDelievered) {
		this.dateDelievered = dateDelievered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getDeliveryAddress() {
		return deliveryAddress;
	}

	public String getDatePurchased() {
		return datePurchased;
	}

	
	
	
	
}
