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
	
	public void joinNewUser(UserVO userVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "INSERT INTO TBL_USER "
					+ "( "
					+ "USERID, "
					+ "USERPW, "
					+ "EMAIL, "
					+ "NAME, "
					+ "ADDRESS, "
					+ "IS_OPEN_TO_MARKETING, "
					+ "BIRTHDAY, "
					+ "GENDER, "
					+ "RANK, "
					+ "AUTHNUM, "
					+ "CREATED_USER "
					+ ") "
					+ "VALUES "
					+ "( "
					+ "?, "
					+ "?, "
					+ "?, "
					+ "?, "
					+ "?, "
					+ "?, "
					+ "?, "
					+ "?, "
					+ "'R', "
					+ "?, "
					+ "'USER' "
					+ ")";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVO.getUserID());
			pstmt.setString(2, userVO.getUserPW());
			pstmt.setString(3, userVO.getEmail());
			pstmt.setString(4, userVO.getName());
			pstmt.setString(5, userVO.getAddress());
			pstmt.setString(6, userVO.getIsOpenToMarketing());
			pstmt.setDate(7, userVO.getBirthday());
			pstmt.setString(8, userVO.getGender());
			pstmt.setString(9, userVO.getAuthNum());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			System.out.println(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int isExistingUser(String userID, String userPW) {
		int resultNum = -1;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT COUNT(*) RESULT_NUM"
					+ "  FROM TBL_USER"
					+ " WHERE 1=1"
					+ "   AND USERID = ?"
					+ "   AND USERPW = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			resultNum = rs.getInt("RESULT_NUM");
			rs.close();
			pstmt.close();
			conn.close();
			System.out.println(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultNum;
	}
	
	public UserVO findUser(String userID, String userPW) {
		UserVO userVO = new UserVO();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT *"
					+ "  FROM TBL_USER"
					+ " WHERE 1=1"
					+ "   AND USERID = ?"
					+ "   AND USERPW = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userVO.setUserID(rs.getString("USERID"));
			userVO.setUserPW(rs.getString("USERPW"));
			userVO.setEmail(rs.getString("EMAIL"));
			userVO.setName(rs.getString("NAME"));
			userVO.setAddress(rs.getString("ADDRESS"));
			userVO.setIsOpenToMarketing(rs.getString("IS_OPEN_TO_MARKETING"));
			userVO.setBirthday(rs.getDate("BIRTHDAY"));
			userVO.setGender(rs.getString("GENDER"));
			userVO.setRank(rs.getString("RANK"));
			userVO.setAuthNum(rs.getString("AUTHNUM"));
			rs.close();
			pstmt.close();
			conn.close();
			System.out.println(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userVO;
	}
}
