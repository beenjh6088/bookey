package com.bookey.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.bookey.utility.EmailController;
import com.bookey.utility.UtilityController;

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
	private ServletContext context;
	List<String> userList;
	
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
		context = getServletContext();
		userList = new ArrayList<>();
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
				Date userBirthday = new Date(UtilityController.formatter.parse(request.getParameter("userBirthday")).getTime());
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
				String userID = request.getParameter("userID");
				String userPW = request.getParameter("userPW");
				HttpSession session = request.getSession(false);
				UserVO userVO = userService.loginUser(userID, userPW);
				
				if(userVO != null) {
					// Login process completed Successfully
//						if(session.isNew()) {
						session.setAttribute("userVO", userVO);
						userList.add(userVO.getUserID());
						context.setAttribute("userList", userList);
//						}
//						nextPage.setLength(0);
//						nextPage.append("/index.jsp");
						
						// the Reason why i use sendRedirect is to block requesting by user's mistake. if not, session overflow with same process(/user/loginUser.do)
						response.sendRedirect(request.getContextPath()+"/index.jsp");
						return;
				}else {
					// Login process completed Unsuccessfully
					pw.print("<script>alert('You have entered incorrect login information. Please log in again.');"
					+ " location.href='"+request.getContextPath()+"/jsp/user/login.jsp';"
					+ "</script>"
					);
					return;
				}
			}else if(action.equals("/logoutUser.do")) {
				HttpSession session = request.getSession(false);
				if(session != null) {
					UserVO userVO = (UserVO)session.getAttribute("userVO");
					userList = (ArrayList<String>) context.getAttribute("userList");
					if(userVO!=null) userList.remove(userVO.getUserID());
					session.invalidate();
				}
				Cookie[] cookies = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("JSESSIONID")) {
	            cookie.setMaxAge(0);
	            cookie.setPath("/");
	            response.addCookie(cookie);
						}
					}
				}
//				nextPage.setLength(0);
//				nextPage.append("/index.jsp");
				
				// the Reason why i use sendRedirect is to block requesting by user's mistake. if not, session overflow with same process(/user/loginUser.do)
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				return;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage.toString());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
