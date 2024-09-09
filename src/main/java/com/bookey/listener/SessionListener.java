package com.bookey.listener;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bookey.user.UserVO;

public class SessionListener extends HttpServlet implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		UserVO userVO = (UserVO)event.getSession().getAttribute("userVO");
		String userID = userVO.getUserID();
		ArrayList<String> userList = (ArrayList<String>) getServletContext().getAttribute("userList");
		userList.remove(userID);
		System.out.println(userID+" is disconnected...");
	}


	
}
