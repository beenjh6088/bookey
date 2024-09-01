package com.bookey.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.bookey.utility.EmailController;

@WebServlet("/user/*")
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8318923147672226562L;
	private UserService userService;
	private EmailController emailController;
	private static final UserController user = new UserController();
	private StringBuilder nextPage = new StringBuilder();
	
	public UserController() {
		getInstance();
	}
	public static UserController getInstance() {
		return user;
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userService = new UserService();
		emailController = new EmailController();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(req, resp);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		String action = request.getPathInfo();
		
		try {
			if(action.equals("/checkForUserID.do")) {
				String userID = request.getParameter("userID");
				JSONObject resultMap = new JSONObject();
				int userCNT = userService.checkForUserID(userID);
				resultMap.put("userCNT", userCNT);
				String strResultMap = resultMap.toJSONString();
				System.out.println(strResultMap);
				pw.print(strResultMap);
				return;
				
			}else if(action.equals("/authenticateEmail.do")) {
				String userEmail = request.getParameter("userEmail");
				// Making 7 digits for an Authentication
				int randomNumber = (int)(Math.random() * 9000000) + 1000000;
				emailController.sendEmail(userEmail);
				JSONObject resultMap = new JSONObject();
				resultMap.put("randomNumber", randomNumber);
				String strResultMap = resultMap.toJSONString();
				System.out.println(strResultMap);
				pw.print(strResultMap);
				return;
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage.toString());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
