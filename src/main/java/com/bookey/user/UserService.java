package com.bookey.user;

public class UserService {

	private UserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAO();
	}
	
	public int checkForUserID(String userID) {
		return userDAO.checkForUserID(userID);
	}
	
	public void joinNewUser(UserVO userVO) {
		userDAO.joinNewUser(userVO);
	}
}
