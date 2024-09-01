package com.bookey.user;

public class UserService {

	private UserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAO();
	}
	
//	public JSONArray getAllKeyword() {
//		return calendarDAO.getAllKeyword();
//	}
	public int checkForUserID(String userID) {
		return userDAO.checkForUserID(userID);
	}
}
