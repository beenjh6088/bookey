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

	public JSONArray selectCategory() {
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

	public JSONArray selectRentalStatus() {
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

	public JSONArray selectBookStatus() {
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

	public JSONArray selectBooks(Map<String, Object> paramMap) {
		JSONArray bookList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			Object PAGESET = paramMap.get("PAGESET") == null ? 10 : paramMap.get("PAGESET");
			Object PAGENUM = paramMap.get("PAGENUM") == null ? 1 : paramMap.get("PAGENUM");

			String query = "" + selectMainQuery(paramMap) + "SELECT MAIN.*"
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
				book.put("BOOKID", rs.getString("BOOKID"));
				book.put("NEXT_USERID", rs.getString("NEXT_USERID"));
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

	public int selectBookTotalAmount(Map<String, Object> paramMap) {
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
			String query = ""
					+ selectMainQuery(paramMap)
					+ "SELECT COUNT(*) AMOUNT FROM MAIN";
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

	public JSONArray selectPageList(Map<String, Object> paramMap) {
		JSONArray pageList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			Object PAGESET = paramMap.get("PAGESET") == null ? 10 : paramMap.get("PAGESET");
			Object PAGENUM = paramMap.get("PAGENUM") == null ? 1 : paramMap.get("PAGENUM");

			String query = ""
					+ selectMainQuery(paramMap)
					+ "SELECT PAGING.*"
					+ "  FROM ("
					+ "        SELECT RECNUM"
					+ "             , PAGENUM"
					+ "             , CEIL(PAGENUM/PAGESET)*PAGESET - PAGESET + 1 SPAGE"
					+ "             , CASE WHEN CEIL(PAGENUM/PAGESET)*PAGESET > CEIL(CNT/PAGESET) THEN CEIL(CNT/PAGESET)"
					+ "                    ELSE CEIL(PAGENUM/PAGESET)*PAGESET"
					+ "                END EPAGE"
					+ "             , CASE WHEN PAGENUM > PAGESET THEN '<' ELSE NULL END PREV"
					+ "             , CASE WHEN CEIL(PAGENUM/PAGESET)*PAGESET > CEIL(CNT/PAGESET) THEN NULL ELSE '>' END NEXT"
					+ "          FROM ("
					+ "                SELECT LEVEL RECNUM"
					+ "                     , COUNT(*) OVER(PARTITION BY 1) CNT"
					+ "                  FROM DUAL"
					+ "                CONNECT BY LEVEL <= (SELECT COUNT(*) CNT FROM MAIN)"
					+ "               ) A"
					+ "             , (SELECT " + PAGENUM + " PAGENUM, " + PAGESET + " PAGESET FROM DUAL) B"
					+ "         WHERE 1=1"
					+ "           AND RECNUM > (PAGENUM-1)*PAGESET"
					+ "           AND RECNUM <= PAGENUM*PAGESET"
					+ "       ) PAGING"
					+ " WHERE 1=1";
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
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageList;
	}

	private String selectMainQuery(Map<String, Object> paramMap) {
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
		String query = "" 
				+ "WITH MAIN AS"
				+ "(" 
				+ "SELECT ROWNUM AS RECNUM" 
				+ "     , MAIN.*" 
				+ "  FROM ("
				+ "        SELECT BOOK.BOOKID" 
				+ "             , BOOK.BOOKNM"
				+ "             , BOOK.PUBLISHER, BOOK.AUTHOR, BOOK.IMAGE_FILE_NAME, BOOK.PUBLISHED_DATE, BOOK.STATUS AS BOOK_STATUS"
				+ "             , LOCA.LOCATION_ID, LOCA_INFO.VALUE LIBRARY_NAME, LOCA.SHELF_NO, LOCA.ROW_NO"
				+ "             , CATEGORY.CATGID, CATEGORY.CATG01, CATEGORY.CATG02, CATEGORY.CATG03"
				+ "             , RENTAL.STATUS AS RENTAL_STATUS_CODE, RENTAL.USERID AS RETAL_USER, RENTAL_STATUS.VALUE AS RENTAL_STATUS_VALUE, RENTAL.RENTAL_DATE, RENTAL.DUE_DATE RENTAL_DUE_DATE"
				+ "             , NVL(WAITING.QUEUE,0) AS QUEUE, WAITING.USERID AS NEXT_USERID"
				+ "             , BOOK_STATUS.CODE BOOK_STATUS_CODE, BOOK_STATUS.VALUE BOOK_STATUS_VALUE, BOOK_APPERANCE.CODE   AS BOOK_APPERANCE_CODE, BOOK_APPERANCE.VALUE AS BOOK_APPERANCE_STATUS"
				+ "          FROM TBL_BOOK BOOK" 
				+ "             , ("
				+ "                SELECT ONGOING.RENTALID, ONGOING.BOOKID, ONGOING.USERID, ONGOING.RENTAL_DATE, ONGOING.DUE_DATE, ONGOING.RETURN_DATE, ONGOING.STATUS"
				+ "                  FROM ("
				+ "                        SELECT RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, RETURN_DATE, STATUS "
				+ "                          FROM (SELECT RANK() OVER(PARTITION BY BOOKID ORDER BY DUE_DATE DESC) RNK, A.* FROM TBL_RENTAL A WHERE 1=1 AND STATUS = 'G') A"
				+ "                         WHERE 1=1"
				+ "                           AND RNK = 1"
				+ "                       ) ONGOING" 
				+ "                 WHERE 1=1"
				+ "               ) RENTAL"
        + "							, ("
				+ "                SELECT ''"
				+ "                     , BOOKID"
				+ "                     , MAX(QUEUE) QUEUE"
				+ "                     , MAX(USERID) KEEP(DENSE_RANK FIRST ORDER BY QUEUE) USERID"
				+ "                  FROM TBL_RENTAL A"
				+ "                 WHERE 1 = 1 "
				+ "                   AND STATUS IS NULL"
				+ "                 GROUP BY BOOKID" 
				+ "               ) WAITING"
				+ "             , TBL_CATEGORY CATEGORY" 
				+ "             , TBL_LOCATION LOCA"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_BOOK' AND COLUMN_NAME = 'STATUS') BOOK_STATUS"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_RENTAL' AND COLUMN_NAME = 'STATUS') RENTAL_STATUS"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_LOCATION' AND COLUMN_NAME = 'PLACE_NO') LOCA_INFO"
				+ "             , (SELECT * FROM TBL_MASTER WHERE 1=1 AND TABLE_NAME = 'TBL_BOOK' AND COLUMN_NAME = 'APPERANCE') BOOK_APPERANCE"
				+ "         WHERE 1=1"
				+ "           AND BOOK.BOOKID = RENTAL.BOOKID(+)"
				+ "           AND BOOK.STATUS = BOOK_STATUS.CODE(+)" 
				+ "           AND BOOK.CATGID = CATEGORY.CATGID(+)"
				+ "           AND RENTAL.STATUS = RENTAL_STATUS.CODE(+)"
				+ "           AND BOOK.BOOKID  = LOCA.BOOKID(+)"
				+ "           AND LOCA.PLACE_NO = LOCA_INFO.CODE(+)" 
				+ "           AND BOOK.APPERANCE = BOOK_APPERANCE.CODE(+)"
				+ "						AND BOOK.BOOKID = WAITING.BOOKID(+)"
				+ "       ) MAIN" 
				+ " WHERE 1=1";
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
	
	public int updateBookStatusCheckout(Map<String, Object> paramMap) {
		int updateResult = -1;
		try {
			String bookID = paramMap.get("bookID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "UPDATE TBL_BOOK "
					+ "   SET STATUS = 'C'"
					+ "     , UPDATED_DATE = SYSDATE"
					+ "     , UPDATED_USER = 'SYSTEM'"
					+ " WHERE 1=1"
					+ "   AND BOOKID = '"+bookID+"'";
			pstmt = conn.prepareStatement(query);
			updateResult = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateResult;
	}
	
	public int insertNewRental(Map<String, Object> paramMap) {
		int updateResult = -1;
		try {
			String bookID = paramMap.get("bookID").toString();
			String userID = paramMap.get("userID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "INSERT INTO TBL_RENTAL"
					+ "("
					+ "       RENTALID"
					+ "     , BOOKID"
					+ "     , USERID"
					+ "     , RENTAL_DATE"
					+ "     , DUE_DATE"
					+ "     , RETURN_DATE"
					+ "     , STATUS"
					+ "     , QUEUE"
					+ "     , CREATED_DATE"
					+ "     , CREATED_USER"
					+ ")"
					+ "SELECT MAX(RENTALID) + 1 AS RENTALID"
					+ "     , '"+bookID+"'			AS BOOKID"
					+ "     , '"+userID+"'			AS USERID"
					+ "     , SYSDATE						AS RENTAL_DATE"
					+ "     , SYSDATE + 14			AS DUE_DATE"
					+ "     , NULL 							AS RETURN_DATE"
					+ "     , 'G' 							AS STATUS"
					+ "     , NULL 							AS QUEUE"
					+ "     , SYSDATE 					AS CREATED_DATE"
					+ "     , 'SYSTEM'					AS CREATED_USER"
					+ "  FROM TBL_RENTAL";
			pstmt = conn.prepareStatement(query);
			updateResult = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateResult;
	}
	
	public int selectNextRentalID(Map<String, Object> paramMap) {
		int rentalID = 0;
		try {
			conn = dataFactory.getConnection();
			String query = ""
					+ "SELECT MAX(RENTALID) + 1 RENTALID "
					+ "  FROM TBL_RENTAL";
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			rentalID = rs.getInt("RENTALID");
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rentalID;
	}
	
	public int insertWaitingRental(Map<String, Object> paramMap) {
		int insertResult = 0;
		try {
			int rentalID = Integer.parseInt(paramMap.get("rentalID").toString());
			String bookID = paramMap.get("bookID").toString();
			String userID = paramMap.get("userID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "INSERT INTO TBL_RENTAL"
					+ "("
					+ "                          		RENTALID,"
					+ "                          		BOOKID,"
					+ "                          		USERID,"
					+ "                          		RENTAL_DATE,"
					+ "                          		DUE_DATE,"
					+ "                          		RETURN_DATE,"
					+ "                          		STATUS,"
					+ "                          		QUEUE,"
					+ "                          		CREATED_DATE,"
					+ "                          		CREATED_USER"
					+ ")"
					+ "SELECT "+rentalID+"       		AS RENTALID"
					+ "     , '"+bookID+"'       		AS BOOKID"
					+ "     , '"+userID+"'       		AS USERID"
					+ "     , NULL               		AS RENTAL_DATE"
					+ "     , NULL               		AS DUE_DATE"
					+ "     , NULL               		AS RETURN_DATE"
					+ "     , NULL               		AS STATUS"
					+ "     , NVL(MAX(QUEUE),0) + 1 AS QUEUE"
					+ "     , SYSDATE            		AS CREATED_DATE"
					+ "     , 'SYSTEM'           		AS CREATED_USER"
					+ "  FROM  TBL_RENTAL"
					+ " WHERE 1=1"
					+ "   AND STATUS IS NULL"
					+ "   AND BOOKID = '"+bookID+"'"
					;
			pstmt = conn.prepareStatement(query);
			insertResult = pstmt.executeUpdate();
			System.out.println(query);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return insertResult;
	}
	
	public JSONArray selectCheckoutList(Map<String, Object> paramMap) {
		JSONArray checkoutList = new JSONArray();
		try {
			String  userID = paramMap.get("userID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "SELECT ROWNUM RECNUM"
					+ "     , A.RENTALID"
					+ "     , A.BOOKID"
					+ "     , A.USERID"
					+ "     , A.RENTAL_DATE"
					+ "     , A.DUE_DATE"
					+ "     , A.STATUS   AS RENTAL_CODE"
					+ "     , C.VALUE    AS RENTAL_VALUE "
					+ "     , B.BOOKNM"
					+ "     , B.IMAGE_FILE_NAME"
					+ "  FROM TBL_RENTAL A"
					+ "     , TBL_BOOK B"
					+ "     , (SELECT * FROM TBL_MASTER WHERE TABLE_NAME = 'TBL_RENTAL' AND COLUMN_NAME = 'STATUS') C"
					+ " WHERE 1=1 "
					+ "   AND A.USERID = '"+userID+"' "
					+ "   AND A.STATUS ='G'"
					+ "   AND A.BOOKID = B.BOOKID"
					+ "   AND A.STATUS = C.CODE";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject checkout = new JSONObject();
				checkout.put("RECNUM", rs.getInt("RECNUM"));
				checkout.put("RENTALID", rs.getInt("RENTALID"));
				checkout.put("BOOKID", rs.getString("BOOKID"));
				checkout.put("USERID", rs.getString("USERID"));
				checkout.put("RENTAL_DATE", rs.getString("RENTAL_DATE"));
				checkout.put("DUE_DATE", rs.getString("DUE_DATE"));
				checkout.put("RENTAL_CODE", rs.getString("RENTAL_CODE"));
				checkout.put("RENTAL_VALUE", rs.getString("RENTAL_VALUE"));
				checkout.put("BOOKNM", rs.getString("BOOKNM"));
				checkout.put("IMAGE_FILE_NAME", rs.getString("IMAGE_FILE_NAME"));
				checkoutList.add(checkout);
			}
			System.out.println(query);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return checkoutList;
	}
	
	public int updateRentalStatusReturn(Map<String, Object> paramMap) {
		int updateResult = 0;
		try {
			String userID = paramMap.get("userID").toString();
			String bookID = paramMap.get("bookID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "UPDATE TBL_RENTAL"
					+ "   SET STATUS 				= CASE WHEN SYSDATE <= DUE_DATE THEN 'R' ELSE 'D' END"
					+ "     , RETURN_DATE		= SYSDATE"
					+ "     , UPDATED_DATE 	= SYSDATE"
					+ "     , UPDATED_USER 	= 'SYSTEM'"
					+ " WHERE 1=1"
					+ "   AND USERID = '"+userID+"'"
					+ "   AND BOOKID = '"+bookID+"'"
					+ "   AND STATUS = 'G'";
			pstmt = conn.prepareStatement(query);
			updateResult = pstmt.executeUpdate();
			System.out.println(query);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateResult;
	}
	
	public int selectQueue(Map<String, Object> paramMap) {
		int queue = -1;
		try {
			String bookID = paramMap.get("bookID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "SELECT COUNT(*) CNT "
					+ "  FROM TBL_RENTAL "
					+ " WHERE 1=1"
					+ "   AND BOOKID = '"+bookID+"'"
					+ "   AND STATUS IS NULL";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			queue = rs.getInt("CNT");
			System.out.println(query);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queue;
	}
	
	public int updateBookStatusReturn(Map<String, Object> paramMap) {
		int updateResult = 0;
		try {
			String bookID = paramMap.get("bookID").toString();
			int queue = Integer.parseInt(paramMap.get("queue").toString());
			conn = dataFactory.getConnection();
			String query = ""
					+ "UPDATE TBL_BOOK"
					+ "   SET STATUS 				= CASE WHEN "+queue+" > 0 THEN 'R' ELSE 'A' END"
					+ "     , UPDATED_DATE 	= SYSDATE"
					+ "     , UPDATED_USER 	= 'SYSTEM'"
					+ " WHERE 1=1"
					+ "   AND BOOKID = '"+bookID+"'";
			pstmt = conn.prepareStatement(query);
			updateResult = pstmt.executeUpdate();
			System.out.println(query);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateResult;
	}
	
	public int updateRentalStatusConfirm(Map<String, Object> paramMap) {
		int updateResult = 0;
		try {
			String bookID = paramMap.get("bookID").toString();
			String userID = paramMap.get("userID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "UPDATE TBL_RENTAL"
					+ "   SET RENTAL_DATE   = SYSDATE"
					+ "     , DUE_DATE      = SYSDATE + 14"
					+ "     , STATUS        = 'G'"
					+ "     , QUEUE         = NULL"
					+ "     , UPDATED_DATE	= SYSDATE"
					+ "     , UPDATED_USER	= 'SYSTEM'"
					+ " WHERE 1=1"
					+ "   AND STATUS IS NULL"
					+ "   AND BOOKID = '"+bookID+"'"
					+ "   AND USERID = '"+userID+"'"
					+ "   AND QUEUE = 1";
			pstmt = conn.prepareStatement(query);
			updateResult = pstmt.executeUpdate();
			System.out.println(query);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateResult;
	}
	
	public int updateWaitingRental(Map<String, Object> paramMap) {
		int updateResult = 0;
		try {
			String bookID = paramMap.get("bookID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "UPDATE TBL_RENTAL"
					+ "   SET QUEUE        = QUEUE -1"
					+ "     , UPDATED_DATE = SYSDATE"
					+ "     , UPDATED_USER = 'SYSTEM'"
					+ " WHERE 1=1"
					+ "   AND STATUS IS NULL"
					+ "   AND BOOKID = '"+bookID+"'";
			pstmt = conn.prepareStatement(query);
			updateResult = pstmt.executeUpdate();
			System.out.println(query);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateResult;
	}
	
	public int updateBookStatusConfirm(Map<String, Object> paramMap) {
		int updateResult = 0;
		try {
			String bookID = paramMap.get("bookID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "UPDATE TBL_BOOK"
					+ "   SET STATUS = 'C'"
					+ "     , UPDATED_DATE = SYSDATE"
					+ "     , UPDATED_USER = 'SYSTEM'"
					+ " WHERE 1=1"
					+ "   AND BOOKID = '"+bookID+"'";
			pstmt = conn.prepareStatement(query);
			updateResult = pstmt.executeUpdate();
			System.out.println(query);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateResult;
	}
	
	public JSONArray selectReservation(Map<String, Object> paramMap) {
		JSONArray reservationList = new JSONArray();
		try {
			String userID = paramMap.get("userID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "SELECT ROWNUM AS RECNUM"
					+ "     , A.RENTALID"
					+ "     , A.BOOKID"
					+ "     , B.BOOKNM "
					+ "     , NVL(A.STATUS, 'NULL') STATUS"
					+ "     , A.QUEUE"
					+ "     , B.IMAGE_FILE_NAME"
					+ "     , A.USERID"
					+ "  FROM TBL_RENTAL A"
					+ "     , TBL_BOOK B"
					+ " WHERE 1=1"
					+ "   AND A.STATUS IS NULL"
					+ "   AND A.USERID = '"+userID+"'"
					+ "   AND A.BOOKID = B.BOOKID";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject reservation = new JSONObject();
				reservation.put("RECNUIM", rs.getInt("RECNUM"));
				reservation.put("RENTALID", rs.getInt("RENTALID"));
				reservation.put("BOOKID", rs.getString("BOOKID"));
				reservation.put("BOOKNM", rs.getString("BOOKNM"));
				reservation.put("STATUS", rs.getString("STATUS"));
				reservation.put("QUEUE", rs.getString("QUEUE"));
				reservation.put("IMAGE_FILE_NAME", rs.getString("IMAGE_FILE_NAME"));
				reservation.put("USERID", rs.getString("USERID"));
				reservationList.add(reservation);
			}
			System.out.println(query);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reservationList;
	}
	
	public int updateRentalStatusTerminated(Map<String, Object> paramMap) {
		int updateRentalStatusTerminatedResult = -1;
		try {
			String bookID = paramMap.get("bookID").toString();
			String userID = paramMap.get("userID").toString();
			conn = dataFactory.getConnection();
			String query = ""
					+ "UPDATE TBL_RENTAL"
					+ "   SET STATUS        = 'T'"
					+ "     , QUEUE         = NULL"
					+ "     , UPDATED_DATE	= SYSDATE"
					+ "     , UPDATED_USER	= 'SYSTEM'"
					+ " WHERE 1=1"
					+ "   AND STATUS IS NULL"
					+ "   AND BOOKID = '"+bookID+"'"
					+ "   AND USERID = '"+userID+"'"
					+ "   AND QUEUE = 1";
			pstmt = conn.prepareStatement(query);
			updateRentalStatusTerminatedResult = pstmt.executeUpdate();
			System.out.println(query);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updateRentalStatusTerminatedResult;
	}
}
