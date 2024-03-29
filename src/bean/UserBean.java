package bean;

public class UserBean {
	private String username;
	private String firstName;
	private String lastName;
	private String address;
	private int addressId;
	private String city;
	private String province;
	private String postal;
	private String accountType;	
	
	public UserBean(String username, String firstName, String lastName, String address, int addressId, String city, String province, String postal, String accountType) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.addressId = addressId;
		this.city = city;
		this.province = province;
		this.postal = postal;
		this.accountType = accountType;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
