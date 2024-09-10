package com.bookey.listener;

import java.util.List;

import javax.servlet.ServletContext;
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
		ServletContext context = event.getSession().getServletContext();
		List<String> userList = (List<String>)context.getAttribute("userList");
		String userID = (String) ((UserVO)event.getSession().getAttribute("userVO")).getUserID();
		
		if(userList != null && userID != null) {
			userList.remove(userID);
			System.out.println(userID+" is disconnected...");
		}
	}
	
}
