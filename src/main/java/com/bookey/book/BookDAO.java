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

public class BookDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public BookDAO() {
		try {
			Context envContext = (Context) (new InitialContext()).lookup("java:/comp/env");
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
			while (rs.next()) {
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
			while (rs.next()) {
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
			String query = "SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_BOOK' AND COLUMN_NAME = 'APPERANCE'";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
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

	public JSONArray searchBooks(Map<String, Object> paramMap) {
		JSONArray bookList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			Object BOOKNM = paramMap.get("BOOKNM");
			Object PUBLISHER = paramMap.get("PUBLISHER");
			Object AUTHOR = paramMap.get("AUTHOR");
			Object CATGID = paramMap.get("CATGID");
			Object RENTAL_STATUS_CODE = paramMap.get("RENTAL_STATUS_CODE");
			Object S_RENTAL_DATE = paramMap.get("S_RENTAL_DATE");
			Object E_RENTAL_DATE = paramMap.get("E_RENTAL_DATE");
			Object S_DUE_DATE = paramMap.get("S_RENTAL_DUE_DATE");
			Object E_DUE_DATE = paramMap.get("E_RENTAL_DUE_DATE");
			Object BOOK_APPERANCE_CODE = paramMap.get("BOOK_APPERANCE_CODE");
			Object PAGESET = paramMap.get("PAGESET") == null ? 10 : paramMap.get("PAGESET");
			Object PAGENUM = paramMap.get("PAGENUM") == null ? 1 : paramMap.get("PAGENUM");

			String query = "" + getMainQuery(paramMap) + "SELECT MAIN.*"
					+ "     , PAGING.PAGENUM, PAGING.SPAGE, PAGING.EPAGE, PAGING.PREV, PAGING.NEXT" + "  FROM MAIN" + "     , ("
					+ "        SELECT RECNUM" + "             , PAGENUM"
					+ "             , CEIL(PAGENUM/PAGESET)*PAGESET - PAGESET + 1 SPAGE"
					+ "             , CASE WHEN CEIL(PAGENUM/PAGESET)*PAGESET > CEIL(CNT/PAGESET) THEN CEIL(CNT/PAGESET)"
					+ "                    ELSE CEIL(PAGENUM/PAGESET)*PAGESET" + "                END EPAGE"
					+ "             , CASE WHEN PAGENUM > PAGESET THEN '<' ELSE NULL END PREV"
					+ "             , CASE WHEN CEIL(PAGENUM/PAGESET)*PAGESET > CEIL(CNT/PAGESET) THEN NULL ELSE '>' END NEXT"
					+ "          FROM (" + "                SELECT LEVEL RECNUM"
					+ "                     , COUNT(*) OVER(PARTITION BY 1) CNT" + "                  FROM DUAL"
					+ "                CONNECT BY LEVEL <= (SELECT COUNT(*) CNT FROM MAIN)" + "               ) A"
					+ "             , (SELECT " + PAGENUM + " PAGENUM, " + PAGESET + " PAGESET FROM DUAL) B"
					+ "         WHERE 1=1" + "           AND RECNUM > (PAGENUM-1)*PAGESET"
					+ "           AND RECNUM <= PAGENUM*PAGESET" + "       ) PAGING" + " WHERE 1=1"
					+ "   AND MAIN.RECNUM = PAGING.RECNUM";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
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
				book.put("BOOK_STATUS_VALUE", rs.getString("BOOK_STATUS_VALUE"));
				book.put("QUEUE", rs.getString("QUEUE"));
				book.put("PUBLISHED_DATE", rs.getString("PUBLISHED_DATE"));
				book.put("BOOK_APPERANCE_STATUS", rs.getString("BOOK_APPERANCE_STATUS"));
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

	public int getBookTotalAmount(Map<String, Object> paramMap) {
		int amount = 0;
		try {
			conn = dataFactory.getConnection();
			Object BOOKNM = paramMap.get("BOOKNM");
			Object PUBLISHER = paramMap.get("PUBLISHER");
			Object AUTHOR = paramMap.get("AUTHOR");
			Object CATGID = paramMap.get("CATGID");
			Object RENTAL_STATUS_CODE = paramMap.get("RENTAL_STATUS_CODE");
			Object S_RENTAL_DATE = paramMap.get("S_RENTAL_DATE");
			Object E_RENTAL_DATE = paramMap.get("E_RENTAL_DATE");
			Object S_DUE_DATE = paramMap.get("S_RENTAL_DUE_DATE");
			Object E_DUE_DATE = paramMap.get("E_RENTAL_DUE_DATE");
			Object BOOK_APPERANCE_CODE = paramMap.get("BOOK_APPERANCE_CODE");
			String query = "" + getMainQuery(paramMap) + "SELECT COUNT(*) AMOUNT FROM MAIN";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			amount = rs.getInt("AMOUNT");
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return amount;
	}

	public JSONArray getPageList(Map<String, Object> paramMap) {
		JSONArray pageList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			Object PAGESET = paramMap.get("PAGESET") == null ? 10 : paramMap.get("PAGESET");
			Object PAGENUM = paramMap.get("PAGENUM") == null ? 1 : paramMap.get("PAGENUM");

			String query = "" + getMainQuery(paramMap) + "SELECT PAGING.*" + "  FROM (" + "        SELECT RECNUM"
					+ "             , PAGENUM" + "             , CEIL(PAGENUM/PAGESET)*PAGESET - PAGESET + 1 SPAGE"
					+ "             , CASE WHEN CEIL(PAGENUM/PAGESET)*PAGESET > CEIL(CNT/PAGESET) THEN CEIL(CNT/PAGESET)"
					+ "                    ELSE CEIL(PAGENUM/PAGESET)*PAGESET" + "                END EPAGE"
					+ "             , CASE WHEN PAGENUM > PAGESET THEN '<' ELSE NULL END PREV"
					+ "             , CASE WHEN CEIL(PAGENUM/PAGESET)*PAGESET > CEIL(CNT/PAGESET) THEN NULL ELSE '>' END NEXT"
					+ "          FROM (" + "                SELECT LEVEL RECNUM"
					+ "                     , COUNT(*) OVER(PARTITION BY 1) CNT" + "                  FROM DUAL"
					+ "                CONNECT BY LEVEL <= (SELECT COUNT(*) CNT FROM MAIN)" + "               ) A"
					+ "             , (SELECT " + PAGENUM + " PAGENUM, " + PAGESET + " PAGESET FROM DUAL) B"
					+ "         WHERE 1=1" + "           AND RECNUM > (PAGENUM-1)*PAGESET"
					+ "           AND RECNUM <= PAGENUM*PAGESET" + "       ) PAGING" + " WHERE 1=1";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject page = new JSONObject();
				page.put("RECNUM", rs.getInt("RECNUM"));
				page.put("PAGENUM", rs.getInt("PAGENUM"));
				page.put("SPAGE", rs.getInt("SPAGE"));
				page.put("EPAGE", rs.getInt("EPAGE"));
				page.put("PREV", rs.getString("PREV"));
				page.put("NEXT", rs.getString("NEXT"));
				pageList.add(page);
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageList;
	}

	private String getMainQuery(Map<String, Object> paramMap) {
		Object BOOKNM = paramMap.get("BOOKNM");
		Object PUBLISHER = paramMap.get("PUBLISHER");
		Object AUTHOR = paramMap.get("AUTHOR");
		Object CATGID = paramMap.get("CATGID");
		Object RENTAL_STATUS_CODE = paramMap.get("RENTAL_STATUS_CODE");
		Object S_RENTAL_DATE = paramMap.get("S_RENTAL_DATE");
		Object E_RENTAL_DATE = paramMap.get("E_RENTAL_DATE");
		Object S_DUE_DATE = paramMap.get("S_RENTAL_DUE_DATE");
		Object E_DUE_DATE = paramMap.get("E_RENTAL_DUE_DATE");
		Object BOOK_APPERANCE_CODE = paramMap.get("BOOK_APPERANCE_CODE");
		String query = "" + "WITH MAIN AS" + "(" + "SELECT ROWNUM AS RECNUM" + "     , MAIN.*" + "  FROM ("
				+ "        SELECT BOOK.BOOKID" + "             , BOOK.BOOKNM"
				+ "             , BOOK.PUBLISHER, BOOK.AUTHOR, BOOK.IMAGE_FILE_NAME, BOOK.PUBLISHED_DATE, BOOK.STATUS AS BOOK_STATUS"
				+ "             , LOCA.LOCATION_ID, LOCA_INFO.VALUE LIBRARY_NAME, LOCA.SHELF_NO, LOCA.ROW_NO"
				+ "             , CATEGORY.CATGID, CATEGORY.CATG01, CATEGORY.CATG02, CATEGORY.CATG03"
				+ "             , RENTAL.STATUS AS RENTAL_STATUS_CODE, RENTAL.USERID AS RETAL_USER, RENTAL_STATUS.VALUE AS RENTAL_STATUS_VALUE, RENTAL.RENTAL_DATE, RENTAL.DUE_DATE RENTAL_DUE_DATE, RENTAL.QUEUE"
				+ "             , BOOK_STATUS.CODE BOOK_STATUS_CODE, BOOK_STATUS.VALUE BOOK_STATUS_VALUE, BOOK_APPERANCE.CODE   AS BOOK_APPERANCE_CODE, BOOK_APPERANCE.VALUE AS BOOK_APPERANCE_STATUS"
				+ "          FROM TBL_BOOK BOOK" + "             , ("
				+ "                SELECT ONGOING.RENTALID, ONGOING.BOOKID, ONGOING.USERID, ONGOING.RENTAL_DATE, ONGOING.DUE_DATE, ONGOING.RETURN_DATE, ONGOING.STATUS"
				+ "                     , WAITING.QUEUE" + "                  FROM ("
				+ "                        SELECT RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, RETURN_DATE, STATUS "
				+ "                          FROM (SELECT RANK() OVER(PARTITION BY BOOKID ORDER BY DUE_DATE DESC) RNK, A.* FROM TBL_RENTAL A WHERE 1=1 AND STATUS = 'G') A"
				+ "                         WHERE 1=1" + "                           AND RNK = 1"
				+ "                       ) ONGOING" + "                     , ("
				+ "                        SELECT BOOKID, MAX(QUEUE) QUEUE" + "                          FROM TBL_RENTAL"
				+ "                         WHERE 1=1" + "                           AND STATUS IS NULL"
				+ "                         GROUP BY BOOKID" + "                       ) WAITING" + "                 WHERE 1=1"
				+ "                   AND ONGOING.BOOKID = WAITING.BOOKID(+)" + "               ) RENTAL"
				+ "             , TBL_CATEGORY CATEGORY" + "             , TBL_LOCATION LOCA"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_BOOK' AND COLUMN_NAME = 'STATUS') BOOK_STATUS"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_RENTAL' AND COLUMN_NAME = 'STATUS') RENTAL_STATUS"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_LOCATION' AND COLUMN_NAME = 'PLACE_NO') LOCA_INFO"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_BOOK' AND COLUMN_NAME = 'APPERANCE') BOOK_APPERANCE"
				+ "         WHERE 1=1" + "           AND BOOK.BOOKID = RENTAL.BOOKID(+)"
				+ "           AND BOOK.STATUS = BOOK_STATUS.CODE(+)" + "           AND BOOK.CATGID = CATEGORY.CATGID(+)"
				+ "           AND RENTAL.STATUS = RENTAL_STATUS.CODE(+)" + "           AND BOOK.BOOKID  = LOCA.BOOKID(+)"
				+ "           AND LOCA.PLACE_NO = LOCA_INFO.CODE(+)" + "           AND BOOK.APPERANCE = BOOK_APPERANCE.CODE(+)"
				+ "       ) MAIN" + " WHERE 1=1";
		if (BOOKNM != null && BOOKNM.toString().length() != 0) {
			query += " AND UPPER(REPLACE(BOOKNM, ' ' ,'')) LIKE UPPER(REPLACE('%" + BOOKNM.toString() + "%', ' ', ''))";
		}
		if (PUBLISHER != null && PUBLISHER.toString().length() != 0) {
			query += " AND UPPER(REPLACE(PUBLISHER, ' ' ,'')) LIKE UPPER(REPLACE('%" + PUBLISHER.toString() + "%', ' ', ''))";
		}
		if (AUTHOR != null && AUTHOR.toString().length() != 0) {
			query += " AND UPPER(REPLACE(AUTHOR, ' ' ,'')) LIKE UPPER(REPLACE('%" + AUTHOR.toString() + "%', ' ', ''))";
		}
		if (CATGID != null && CATGID.toString().length() != 0) {
			query += " AND CATGID = '" + CATGID.toString() + "'";
		}
		if (RENTAL_STATUS_CODE != null && RENTAL_STATUS_CODE.toString().length() != 0) {
			query += " AND RENTAL_STATUS_CODE = '" + RENTAL_STATUS_CODE.toString() + "'";
		}
		if (S_RENTAL_DATE != null && S_RENTAL_DATE.toString().length() != 0 && E_RENTAL_DATE != null
				&& E_RENTAL_DATE.toString().length() != 0) {
			query += " AND RENTAL_DATE BETWEEN TO_DATE('" + S_RENTAL_DATE.toString() + "', 'YYYY-MM-DD') AND TO_DATE('"
					+ E_RENTAL_DATE + "', 'YYYY-MM-DD')";
		}
		if (S_DUE_DATE != null && S_DUE_DATE.toString().length() != 0 && E_DUE_DATE != null
				&& E_DUE_DATE.toString().length() != 0) {
			query += " AND RENTAL_DUE_DATE BETWEEN TO_DATE('" + S_DUE_DATE.toString() + "', 'YYYY-MM-DD') AND TO_DATE('"
					+ E_DUE_DATE + "', 'YYYY-MM-DD')";
		}
		if (BOOK_APPERANCE_CODE != null && BOOK_APPERANCE_CODE.toString().length() != 0) {
			query += " AND BOOK_APPERANCE_CODE = '" + BOOK_APPERANCE_CODE.toString() + "'";
		}
		query += "" + ")";
		return query;
	}
}
