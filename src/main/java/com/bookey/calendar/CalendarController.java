package com.bookey.calendar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/calendar/*")
public class CalendarController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8318923147672226562L;
	private CalendarService calendarService;
	private static final CalendarController calendar = new CalendarController();
	private StringBuilder nextPage = new StringBuilder();
	
	public CalendarController() {
		getInstance();
	}
	public static CalendarController getInstance() {
		return calendar;
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		calendarService = new CalendarService();
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
			if(action == null || action.equals("/getAllDayOffs.do")) {
				JSONObject resultMap = new JSONObject();
				JSONArray dayOffsList = calendarService.getAllDayOffs();
				resultMap.put("dayOffsList", dayOffsList);
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
