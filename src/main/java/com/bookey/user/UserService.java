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
	
	public UserVO loginUser(String userID, String userPW) {
		UserVO userVO = null;
		int resultNum = userDAO.isExistingUser(userID, userPW);
		if(resultNum == 1) {
			// Login process completed Successfully
			userVO = userDAO.findUser(userID, userPW);
		}
		return userVO;
	}
}
