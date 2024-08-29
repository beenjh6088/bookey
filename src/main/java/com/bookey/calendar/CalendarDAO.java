package com.bookey.calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CalendarDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public CalendarDAO() {
		try {
			Context envContext = (Context)(new InitialContext()).lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JSONArray getAllDayOffs() {
		JSONArray calendarList = new JSONArray();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM TBL_CALENDAR WHERE 1=1 AND ISDAYOFF = 'Y'";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject calendar = new JSONObject();
				calendar.put("DATEID", rs.getString("DATEID"));
				calendar.put("DT", rs.getDate("DT").toString());
				calendar.put("DAYNUM", rs.getInt("DAYNUM"));
				calendar.put("DAYCHA", rs.getString("DAYCHA"));
				calendar.put("YYYY", rs.getString("YYYY"));
				calendar.put("MM", rs.getString("MM"));
				calendar.put("DD", rs.getString("DD"));
				calendar.put("ISDAYOFF", rs.getString("ISDAYOFF"));
				calendar.put("REMARK", rs.getString("REMARK"));
				calendarList.add(calendar);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendarList;
	}
}
