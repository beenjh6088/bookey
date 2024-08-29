package com.bookey.calendar;

import org.json.simple.JSONArray;

public class CalendarService {

	private CalendarDAO calendarDAO;
	
	public CalendarService() {
		calendarDAO = new CalendarDAO();
	}
	
	public JSONArray getAllDayOffs() {
		return calendarDAO.getAllDayOffs();
	}
}
