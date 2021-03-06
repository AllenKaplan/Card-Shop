package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

public class UserDAO {
	private DataSource dataSource;

	public UserDAO() throws ClassNotFoundException {
		try {
			dataSource = (DataSource) (new InitialContext()).lookup(DatabaseAccess.instance.ACTIVE_ACCESS);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	} 

	/**
	 * Tries to login
	 * @param username
	 * @param password
	 * @return A user bean consisting of the account info that logged in. If no account found, returns null
	 * @throws SQLException 
	 */
	public UserBean login(String username, String password) throws SQLException {
		UserBean user = null;
		String queryAccount = "select * from logins join accounts on logins.username = accounts.username where logins.username = ? and logins.password = ?";
		Connection con = this.dataSource.getConnection();   

		PreparedStatement login = con.prepareStatement(queryAccount);   
		login.setString(1, username);	
		login.setString(2, password);	
		
		ResultSet userResult = login.executeQuery();  

		if (userResult.next()) //if an account exists
		{    
			String fname = userResult.getString("fname");
			String lname = userResult.getString("lname");
			String accountType = userResult.getString("accountType");

			Integer address = userResult.getInt("address");
			String street = null;
			int addressId = -1;
			String city = null; 
			String province = null; 
			String postal = null;

			//Query for address details
			String addQuery = "select * from addresses where addressID = ?";
			PreparedStatement statmentAddress = con.prepareStatement(addQuery);   
			statmentAddress.setInt(1, address);
			ResultSet addResult = statmentAddress.executeQuery();  
			if (addResult.next())
			{
				street = addResult.getString("street");
				addressId = addResult.getInt("addressID");
				city = addResult.getString("city");
				province = addResult.getString("province");
				postal = addResult.getString("zip");
			}
			user = new UserBean(username, fname, lname, street, addressId, city, province, postal, accountType);
			addResult.close();   
			statmentAddress.close();   
		}   
		userResult.close();   
		login.close();   
		con.close(); 
		return user;   		
	}

	/**
	 * Registers a user with the customer account type
	 * @param username
	 * @param password
	 * @param newUser - the user to be registered
	 * 
	 * @throws throws exception if failed to register
	 */
	public void register(String username, String password, UserBean newUser) throws Exception {
		Connection con = this.dataSource.getConnection();   

		//Check if exists
		String checkExists = "select * from logins where username = ?";
		PreparedStatement check = con.prepareStatement(checkExists);   
		check.setString(1, username);
		ResultSet result = check.executeQuery();
		if (result.next())
		{
			check.close();
			result.close();
			throw new Exception("username already exists");
		}
		else
		{
			//Add Login
			String addLoginQuery = "insert into logins (username,password) values (?,?)";
			PreparedStatement addLogin = con.prepareStatement(addLoginQuery);   
			addLogin.setString(1, username);
			addLogin.setString(2, password);
			addLogin.executeUpdate();

			//Add Address
			String addAddressQuery = "insert into Addresses (street,province,city,zip) values (?,?,?,?)";
			PreparedStatement addAddress = con.prepareStatement(addAddressQuery);   
			addAddress.setString(1, newUser.getAddress());
			addAddress.setString(2, newUser.getProvince());
			addAddress.setString(3, newUser.getCity());
			addAddress.setString(4, newUser.getPostal());
			addAddress.executeUpdate();
			String addressCount = "select max(addressID) from Addresses";
			PreparedStatement getCount = con.prepareStatement(addressCount);   
			ResultSet r = getCount.executeQuery();
			int count = 1; 
			while (r.next())
			{
				count = r.getInt(1);
			}	

			//Add Account
			String addAccountQuery = "insert into Accounts (username, fname, lname, email, address, phone, accountType) values (?,?,?,'',?,'',?)";
			PreparedStatement addAccount = con.prepareStatement(addAccountQuery);   
			addAccount.setString(1, username);
			addAccount.setString(2, newUser.getFirstName());
			addAccount.setString(3, newUser.getLastName());
			addAccount.setInt(4, count);
			addAccount.setString(5, newUser.getAccountType());
			addAccount.executeUpdate();		

			check.close();
			result.close();
			addLogin.close();  
			addAddress.close();
			addAccount.close();
			r.close();
			con.close(); 
		}
	}

	/**
	 * Updates name and address for the given user 
	 * @param updatedUser - contains info that should be updated
	 */
	public void updateUser(UserBean updatedUser) throws Exception {
		UserBean user = null;
		String queryAccount = "select address from accounts where username = ?";

		Connection con = this.dataSource.getConnection();   

		PreparedStatement getAddress = con.prepareStatement(queryAccount);   
		getAddress.setString(1, updatedUser.getUsername());	
		System.out.println("************\n" + updatedUser.getUsername() + "\n**********************");
		ResultSet addressResult = getAddress.executeQuery();  

		int address;
		if (addressResult.next()) //if an address exists
		{
			address = addressResult.getInt("address");
		} else {
			throw new Exception("Could not find address");
		}

		addressResult.close();   
		getAddress.close();   

		String updateAccount = "update accounts set fname = ?, lname = ? where username = ?";
		PreparedStatement updateName = con.prepareStatement(updateAccount);   
		updateName.setString(1, updatedUser.getFirstName());
		updateName.setString(2, updatedUser.getLastName());
		updateName.setString(3, updatedUser.getUsername());
		updateName.executeUpdate(); 
		updateName.close();

		String updateAddress = "update addresses set street = ?, province = ?, city = ?, zip = ? where addressId = ?";
		PreparedStatement updateAddressInfo = con.prepareStatement(updateAddress);
		updateAddressInfo.setString(1, updatedUser.getAddress());
		updateAddressInfo.setString(2, updatedUser.getProvince());
		updateAddressInfo.setString(3, updatedUser.getCity());
		updateAddressInfo.setString(4, updatedUser.getPostal());
		updateAddressInfo.setInt(5, address);
		updateAddressInfo.close();
		con.close(); 
	}
}
