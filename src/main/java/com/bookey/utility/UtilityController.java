package com.bookey.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.JSONParser;

@WebServlet("/utility/*")
public class UtilityController extends HttpServlet {
	public static JSONParser jsonParser = new JSONParser();
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
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
		String action = request.getPathInfo();
		if(action.equals("/imageDownload.do")) {
			String imageFileName = (String) request.getParameter("imageFileName");
			OutputStream out = response.getOutputStream();
			// get the real path on file system for web server
			String bookImageRepo = getServletContext().getRealPath("/image/book");
			String bookImagePath = bookImageRepo+"\\"+imageFileName;
			System.out.println("bookImagePath : "+bookImagePath);
			File imageFile = new File(bookImagePath);
			
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment;fileName="+imageFileName);
			FileInputStream in = new FileInputStream(imageFile);
			byte[] buffer = new byte[1024*8];
			while(true) {
				int count = in.read(buffer);
				if(count == -1) {
					break;
				}
				out.write(buffer, 0, count);
			}
			in.close();
			out.close();
			return;
		}
	}
	
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
    Map<String, Object> paramMap = new HashMap<>();
    
    // get all of the parameter names
    Enumeration<String> parameterNames = request.getParameterNames();
    
    // put all of the attributes and values in the map, while iterating
    while (parameterNames.hasMoreElements()) {
        String paramName = parameterNames.nextElement();
        String paramValue = request.getParameter(paramName); // 하나의 값만 가져옴
        paramMap.put(paramName, paramValue);
    }
    
    return paramMap;
}
}
