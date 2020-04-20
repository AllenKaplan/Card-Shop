package bean;

/**
 * Stores a review about a card
 */
public class ReviewBean {
	private int cardID;
	private int rating;
	private String content; 
	
	
	public ReviewBean(int cardID, int rating, String content) {
		super();
		this.cardID = cardID;
		this.rating = rating;
		this.content = content;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCardID() {
		return cardID;
	}
	
}
