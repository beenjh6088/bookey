package com.bookey.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bookey.utility.UtilityController;

@WebServlet("/book/*")
public class BookController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8318923147672226562L;
	private BookService bookService;
	private static final BookController book = new BookController();
	private StringBuilder nextPage = new StringBuilder();
	private ServletContext context;
	
	public BookController() {
		getInstance();
	}
	public static BookController getInstance() {
		return book;
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		bookService = new BookService();
		context = getServletContext();
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
			if(action.equals("/loadCategory.do")) {
				JSONObject resultMap = new JSONObject();
				JSONArray categoryList = bookService.loadCategory();
				resultMap.put("categoryList", categoryList);
				String strResultMap = resultMap.toJSONString();
				System.out.println(strResultMap);
				pw.print(strResultMap);
				return;
				
			}else if(action.equals("/loadRentalStatus.do")) {
				JSONObject resultMap = new JSONObject();
				JSONArray rentalStatusList = bookService.loadRentalStatus();
				resultMap.put("rentalStatusList", rentalStatusList);
				String strResultMap = resultMap.toJSONString();
				System.out.println(strResultMap);
				pw.print(strResultMap);
				return;
				
			}else if(action.equals("/loadBookStatus.do")) {
				JSONObject resultMap = new JSONObject();
				JSONArray bookStatusList = bookService.loadBookStatus();
				resultMap.put("bookStatusList", bookStatusList);
				String strResultMap = resultMap.toJSONString();
				System.out.println(strResultMap);
				pw.print(strResultMap);
				return;
				
			}else if(action.equals("/searchBooks.do")) {
				Map<String, Object> requestParm = UtilityController.getParameterMap(request);
				String strFrmData = requestParm.get("frmData").toString();
				JSONObject paramMap = (JSONObject) UtilityController.jsonParser.parse(strFrmData);
				int bookTotalAmount = bookService.getBookTotalAmount(paramMap);
				JSONArray bookList = bookService.searchBooks(paramMap);
				JSONArray pageList = bookService.getPageList(paramMap);
				JSONObject resultMap = new JSONObject();
				resultMap.put("bookTotalAmount", bookTotalAmount);
				resultMap.put("bookList", bookList);
				resultMap.put("pageList", pageList);
				String strResultMap = resultMap.toJSONString().replaceAll("null", "\"\"");
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
