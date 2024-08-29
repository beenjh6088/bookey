package com.bookey.keyword;

import org.json.simple.JSONArray;

public class KeywordService {

	private KeywordDAO calendarDAO;
	
	public KeywordService() {
		calendarDAO = new KeywordDAO();
	}
	
	public JSONArray getAllKeyword() {
		return calendarDAO.getAllKeyword();
	}
}
