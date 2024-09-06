package com.bookey.book;

import java.util.Map;

import org.json.simple.JSONArray;

public class BookService {

	private BookDAO bookDAO;
	
	public BookService() {
		bookDAO = new BookDAO();
	}
	
	public JSONArray loadCategory() {
		return bookDAO.loadCategory();
	}
	
	public JSONArray loadRentalStatus() {
		return bookDAO.loadRentalStatus();
	}
	
	public JSONArray loadBookStatus() {
		return bookDAO.loadRentalStatus();
	}
	
	public JSONArray searchBooks(Map<String, Object> filterMap) {
		return bookDAO.searchBooks(filterMap);
	}
	
}
