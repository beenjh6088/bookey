package com.bookey.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bookey.utility.UtilityController;

public class BookDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public BookDAO() {
		try {
			Context envContext = (Context)(new InitialContext()).lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JSONArray loadCategory() {
		JSONArray categoryList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM TBL_CATEGORY";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject book = new JSONObject();
				book.put("CATGID", rs.getString("CATGID"));
				book.put("CATG01", rs.getString("CATG01"));
				book.put("CATG02", rs.getString("CATG02"));
				book.put("CATG03", rs.getString("CATG03"));
				book.put("REMARK01", rs.getString("REMARK01"));
				book.put("REMARK02", rs.getString("REMARK02"));
				book.put("REMARK03", rs.getString("REMARK03"));
				categoryList.add(book);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return categoryList;
	}
	
	public JSONArray loadRentalStatus() {
		JSONArray rentalStatusList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_RENTAL' AND COLUMN_NAME = 'STATUS'";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject rentalStatus = new JSONObject();
				rentalStatus.put("CODE", rs.getString("CODE"));
				rentalStatus.put("VALUE", rs.getString("VALUE"));
				rentalStatusList.add(rentalStatus);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rentalStatusList;
	}
	
	public JSONArray loadBookStatus() {
		JSONArray bookStatusList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_BOOK' AND COLUMN_NAME = 'STATUS'";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject bookStatus = new JSONObject();
				bookStatus.put("CODE", rs.getString("CODE"));
				bookStatus.put("VALUE", rs.getString("VALUE"));
				bookStatusList.add(bookStatus);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bookStatusList;
	}
	
	public JSONArray searchBooks(Map<String, Object> filterMap) {
		JSONArray bookList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			Object BOOKNM = filterMap.get("BOOKNM");
			Object PUBLISHER = filterMap.get("PUBLISHER");
			Object AUTHOR = filterMap.get("AUTHOR");
			Object CATGID = filterMap.get("CATGID");
			Object RENTAL_STATUS_CODE = filterMap.get("RENTAL_STATUS_CODE");
			Object S_RENTAL_DATE = filterMap.get("S_RENTAL_DATE");
			Object E_RENTAL_DATE = filterMap.get("E_RENTAL_DATE");
			Object S_DUE_DATE = filterMap.get("S_DUE_DATE");
			Object E_DUE_DATE = filterMap.get("E_DUE_DATE");
			Object BOOK_STATUS_CODE = filterMap.get("BOOK_STATUS_CODE");
			
			String query = "SELECT *"
					+ "  FROM ("
					+ "        SELECT ROWNUM AS RECNUM"
					+ "             , BOOK.BOOKID"
					+ "             , BOOK.BOOKNM"
					+ "             , BOOK.PUBLISHER, BOOK.AUTHOR, BOOK.IMAGE_FILE_NAME, BOOK.PUBLISHED_DATE"
					+ "             , LOCA.LOCATION_ID, LOCA_INFO.VALUE LIBRARY_NAME, LOCA.SHELF_NO, LOCA.ROW_NO"
					+ "             , CATEGORY.CATGID, CATEGORY.CATG01, CATEGORY.CATG02, CATEGORY.CATG03"
					+ "             , RENTAL.RENTALID AS RENTAL_STATUS_CODE, RENTAL.USERID AS RETAL_USER, RENTAL_STATUS.VALUE AS RENTAL_STATUS_VALUE, TO_CHAR(RENTAL.RENTAL_DATE, 'YYYY-MM-DD') AS RENTAL_DATE, TO_CHAR(RENTAL.DUE_DATE, 'YYYY-MM-DD') AS RENTAL_DUE_DATE"
					+ "             , BOOK_STATUS.CODE BOOK_STATUS_CODE, BOOK_STATUS.VALUE BOOK_STATUS"
					+ "          FROM TBL_BOOK BOOK"
					+ "             , TBL_RENTAL RENTAL"
					+ "             , TBL_CATEGORY CATEGORY"
					+ "             , TBL_LOCATION LOCA"
					+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_BOOK' AND COLUMN_NAME = 'STATUS') BOOK_STATUS"
					+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_RENTAL' AND COLUMN_NAME = 'STATUS') RENTAL_STATUS"
					+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_LOCATION' AND COLUMN_NAME = 'PLACE_NO') LOCA_INFO"
					+ "         WHERE 1=1"
					+ "           AND BOOK.BOOKID = RENTAL.BOOKID(+)"
					+ "           AND BOOK.STATUS = BOOK_STATUS.CODE(+)"
					+ "           AND BOOK.CATGID = CATEGORY.CATGID(+)"
					+ "           AND RENTAL.STATUS = RENTAL_STATUS.CODE(+)"
					+ "           AND BOOK.BOOKID  = LOCA.BOOKID(+)"
					+ "           AND LOCA.PLACE_NO = LOCA_INFO.CODE(+)"
					+ "       )"
					+ " WHERE 1=1";
					if(BOOKNM != null && BOOKNM.toString().length() != 0) {
						query += " AND BOOKNM LIKE '%"+BOOKNM.toString()+"%'";
					}
					if(PUBLISHER != null && PUBLISHER.toString().length() != 0) {
						query += " AND BOOKNM LIKE '%"+PUBLISHER.toString()+"%'";
					}
					if(AUTHOR != null && AUTHOR.toString().length() != 0) {
						query += " AND BOOKNM LIKE '%"+AUTHOR.toString()+"%'";
					}
					if(CATGID != null && CATGID.toString().length() != 0) {
						query += " AND CATGID = '"+CATGID.toString()+"'";
					}
					if(RENTAL_STATUS_CODE != null && RENTAL_STATUS_CODE.toString().length() != 0) {
						query += " AND RENTAL_STATUS_CODE = '"+RENTAL_STATUS_CODE.toString()+"'";
					}
					if(S_RENTAL_DATE != null && S_RENTAL_DATE.toString().length() != 0 && E_RENTAL_DATE != null && E_RENTAL_DATE.toString().length() != 0) {
						query += " AND RENTAL_DATE BETWEEN TO_DATE('"+S_RENTAL_DATE.toString()+"', 'YYYY-MM-DD') AND TO_DATE('"+E_RENTAL_DATE+"', 'YYYY-MM-DD')";
					}
					if(S_DUE_DATE != null && S_DUE_DATE.toString().length() != 0 && E_DUE_DATE != null && E_DUE_DATE.toString().length() != 0) {
						query += " AND RENTAL_DUE_DATE BETWEEN TO_DATE('"+S_DUE_DATE.toString()+"', 'YYYY-MM-DD') AND TO_DATE('"+E_DUE_DATE+"', 'YYYY-MM-DD')";
					}
					if(BOOK_STATUS_CODE != null && BOOK_STATUS_CODE.toString().length() != 0) {
						query += " AND BOOK_STATUS_CODE = '"+BOOK_STATUS_CODE.toString()+"'";
					}
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject book = new JSONObject();
				book.put("RECNUM", rs.getInt("RECNUM"));
				book.put("BOOKNM", rs.getString("BOOKNM"));
				book.put("PUBLISHER", rs.getString("PUBLISHER"));
				book.put("AUTHOR", rs.getString("AUTHOR"));
				book.put("CATGID", rs.getString("CATGID"));
				book.put("CATG01", rs.getString("CATG01"));
				book.put("CATG02", rs.getString("CATG02"));
				book.put("CATG03", rs.getString("CATG03"));
				book.put("RENTAL_STATUS_CODE", rs.getString("RENTAL_STATUS_CODE"));
				book.put("RETAL_USER", rs.getString("RETAL_USER"));
				book.put("RENTAL_STATUS_VALUE", rs.getString("RENTAL_STATUS_VALUE"));
				book.put("RENTAL_DATE", rs.getString("RENTAL_DATE"));
				book.put("RENTAL_DUE_DATE", rs.getString("RENTAL_DUE_DATE"));
				book.put("BOOK_STATUS_CODE", rs.getString("BOOK_STATUS_CODE"));
				book.put("BOOK_STATUS", rs.getString("BOOK_STATUS"));
				book.put("IMAGE_FILE_NAME", rs.getString("IMAGE_FILE_NAME"));
				book.put("LIBRARY_NAME", rs.getString("LIBRARY_NAME"));
				book.put("BOOK_STATUS", rs.getString("BOOK_STATUS"));
				bookList.add(book);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bookList;
	}
}
