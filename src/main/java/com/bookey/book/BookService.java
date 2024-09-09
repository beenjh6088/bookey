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
		return bookDAO.loadBookStatus();
	}
	
	public JSONArray searchBooks(Map<String, Object> paramMap) {
		return bookDAO.searchBooks(paramMap);
	}
	
	public int getBookTotalAmount(Map<String, Object> paramMap) {
		return bookDAO.getBookTotalAmount(paramMap);
	}
	
	public JSONArray getPageList(Map<String, Object> paramMap) {
		return bookDAO.getPageList(paramMap);
	}
	
	public int checkOutBook(Map<String, Object> paramMap) {
		int checkOutResult = bookDAO.checkOutBookStatus(paramMap);
		int addRentalResult  = bookDAO.addRental(paramMap);
		return checkOutResult * addRentalResult;
	}
	
	public int reserveBook(Map<String, Object> paramMap) {
		int rentalID = bookDAO.getNextRentalID(paramMap);
		paramMap.put("rentalID", rentalID);
		int reserveResult = bookDAO.reserveBook(paramMap);
		return reserveResult;
	}
	
	public JSONArray getCheckoutList(Map<String, Object> paramMap) {
		return bookDAO.getCheckoutList(paramMap);
	}
}
