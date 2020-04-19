package model;

import bean.UserBean;
import dao.UserDAO;

public class UserModel {
	private UserDAO userDAO;
	
	public UserModel() throws Exception {
		userDAO = new UserDAO();
	}
	
	public UserBean login(String username, String password) throws Exception {
		String[] inputs = { username, password };
		this.verifyInputs(inputs);
		return userDAO.login(username, password);
	}
	
	public void register(String username, String password, 
			String firstName, String lastName, String address, 
			String city, String province, String postal) throws Exception {
		String[] inputs = { username, password, firstName, lastName, address, city, province, postal };
		this.verifyInputs(inputs);
		if (password.length() < 6) {
			throw new Exception("Password must be 6 or more characters");
		}
		UserBean newUser = new UserBean(firstName, lastName, address, city, province, postal, "CUSTOMER");
		userDAO.register(username, password, newUser);
	}
	
	public void updateAddress(String firstName, String lastName, String address, 
			String city, String province, String postal) throws Exception {
		
	}
	
	private void verifyInputs(String[] inputs) throws Exception {
		for (String input: inputs) {
			if (input == null || input.equals("")) {
				throw new Exception("Inputs cannot be empty");
			} else if (input.contains(";")) {
				throw new Exception("Input cannot contain ;");
			} else if (input.toUpperCase().contains(" OR ") || input.toUpperCase().contains(" AND ")) {
				throw new Exception("Input cannot contain SQL commands.");
			}
		}
	}
}
