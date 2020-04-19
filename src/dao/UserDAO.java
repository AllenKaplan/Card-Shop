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
				city = addResult.getString("city");
				province = addResult.getString("province");
				postal = addResult.getString("zip");
			}
			user = new UserBean(fname, lname, street, city, province, postal, accountType);
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
		String addLoginQuery = "insert into logins (username,password) values (?,?)";
		
		//Add Login
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
		String addressCount = "select count(addressID) from Addresses";
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
		
		addLogin.close();  
		addAddress.close();
		addAccount.close();
		r.close();
		con.close(); 
	}
}
