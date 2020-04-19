package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

public class UserDAO {
	private DataSource dataSource;

	public UserDAO() throws ClassNotFoundException {
		try {
			dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tries to login
	 * @param username
	 * @param password
	 * @return The type (customer, admin, or partner) of the account that logged in. If no account found, returns null
	 */
	public String login(String username, String password) {
		return (username.equals("user") && password.equals("password")) ? "customer" : null;
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
		
	}
}
