package com.bookey.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public UserDAO() {
		try {
			Context envContext = (Context)(new InitialContext()).lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int checkForUserID(String userID) {
		int userCNT = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT COUNT(*) USERCNT"
					+ "  FROM TBL_USER"
					+ " WHERE 1=1"
					+ "   AND USERID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userCNT = rs.getInt("USERCNT");
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userCNT;
	}
}
