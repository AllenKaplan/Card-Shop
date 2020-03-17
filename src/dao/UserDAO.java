package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	private DataSource ds;

	public UserDAO() throws ClassNotFoundException {
//		try {
//			ds = (DataSource) (new InitialContext()).lookup("?"); // ? was java:/comp/env/jdbc/EECS but this needs to be changed
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
		
	}
	
	public boolean login(String username, String password) {
		return (username.equals("Jer") && password.equals("notKevin"));
	}
}
