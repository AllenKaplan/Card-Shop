package bean;

/**
 * Class retrieves information about past purchase totals for a specific card 
 */
public class PurchaseHistoryBean {
	private int cardNumber; 
	private String cardName;
	private double purchasePrice;
	private int quantitySold;
	private String image;
	
	public PurchaseHistoryBean(int cardNumber,String cardName,double purchasePrice, int quantitySold,String image)
	{
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.purchasePrice = purchasePrice;
		this.quantitySold = quantitySold;
		this.image = image;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public int getQuantitySold() {
		return quantitySold;
	}

	public String getImage() {
		return image;
	}
}
	