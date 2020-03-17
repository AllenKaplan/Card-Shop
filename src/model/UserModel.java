package model;

import dao.UserDAO;

public class UserModel {
	private UserDAO userDAO;
	
	public UserModel() throws Exception {
		userDAO = new UserDAO();
	}
	
	public boolean login(String username, String password) throws Exception {
		String[] inputs = { username, password };
		this.verifyInputs(inputs);
		return userDAO.login(username, password);
	}
	
	private void verifyInputs(String[] inputs) throws Exception {
		for (String input: inputs) {
			if (input.contains(";"))
				throw new Exception("Input cannot contain ;");
		}
	}
}
