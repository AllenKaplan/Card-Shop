package bean;

public class UserPurchasesBean {
	private String zipcode; 
	private double totalSpent; 
	
	/**
	 * Used to get information about user purchases. For privacy reasons, only holds users zipcode to identify them
	 * @param zipcode
	 * @param totalSpent
	 */
	public UserPurchasesBean(String zipcode, double totalSpent)
	{
		this.zipcode = zipcode; 
		this.totalSpent = totalSpent; 
	}

	public String getZipcode() {
		return zipcode;
	}

	public double getTotalSpent() {
		return totalSpent;
	}
	
}
