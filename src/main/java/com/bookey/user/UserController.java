package com.bookey.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private HttpSession session;
	
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
				emailController.sendForEmail(userEmail, randomNumber);
				JSONObject resultMap = new JSONObject();
				resultMap.put("randomNumber", randomNumber);
				String strResultMap = resultMap.toJSONString();
				System.out.println(strResultMap);
				pw.print(strResultMap);
				return;
				
			}else if(action.equals("/joinNewUser.do")) {
				String userID = request.getParameter("userID");
				String userPW = request.getParameter("userPW");
				String userEmail = request.getParameter("userEmail");
				String userName = request.getParameter("userName");
				String userAddress = request.getParameter("userAddress");
				Date userBirthday = new Date(formatter.parse(request.getParameter("userBirthday")).getTime());
				String userGender = request.getParameter("userGender");
				String userMarketing = request.getParameter("userMarketing");
				String userAuthentication = request.getParameter("userAuthentication");
				UserVO userVO = new UserVO();
				userVO.setUserID(userID);
				userVO.setUserPW(userPW);
				userVO.setEmail(userEmail);
				userVO.setName(userName);
				userVO.setAddress(userAddress);
				userVO.setBirthday(userBirthday);
				userVO.setGender(userGender);
				userVO.setIsOpenToMarketing(userMarketing);
				userVO.setAuthNum(userAuthentication);
				userService.joinNewUser(userVO);
				pw.print("<script>alert('Your Registration has been successful.');"
						+ " location.href='"+request.getContextPath()+"/jsp/user/login.jsp';"
						+ "</script>"
						);
				return;
			}else if(action.equals("/loginUser.do")) {
				System.out.println("dddddd");
				nextPage.setLength(0);
				nextPage.append("/index.jsp");
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage.toString());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
