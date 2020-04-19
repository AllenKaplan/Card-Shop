package model;

import bean.UserBean;
import dao.UserDAO;

public class UserModel {
	private UserDAO userDAO;
	
	public UserModel() throws Exception {
		userDAO = new UserDAO();
	}
	
	public String login(String username, String password) throws Exception {
		String[] inputs = { username, password };
		this.verifyInputs(inputs);
		return userDAO.login(username, password);
	}
	
	public void register(String username, String password, 
			String firstName, String lastName, String address, 
			String city, String province, String postal) throws Exception {
		String[] inputs = { username, password, firstName, lastName, address, city, province, postal };
		this.verifyInputs(inputs);
		UserBean newUser = new UserBean(firstName, lastName, address, city, province, postal);
		userDAO.register(username, password, newUser);
	}
	
	private void verifyInputs(String[] inputs) throws Exception {
		for (String input: inputs) {
			if (input.contains(";"))
				throw new Exception("Input cannot contain ;");
			else if (input.toUpperCase().contains(" OR ") || input.toUpperCase().contains(" AND ")) {
				throw new Exception("Input cannot contain SQL commands.");
			}
		}
	}
}
