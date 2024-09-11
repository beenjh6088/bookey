package com.bookey.book;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

public class BookService {

	private BookDAO bookDAO;
	
	public BookService() {
		bookDAO = new BookDAO();
	}
	
	public JSONArray loadCategory() {
		return bookDAO.selectCategory();
	}
	
	public JSONArray loadRentalStatus() {
		return bookDAO.selectRentalStatus();
	}
	
	public JSONArray loadBookStatus() {
		return bookDAO.selectBookStatus();
	}
	
	public JSONArray searchBooks(Map<String, Object> paramMap) {
		return bookDAO.selectBooks(paramMap);
	}
	
	public int getBookTotalAmount(Map<String, Object> paramMap) {
		return bookDAO.selectBookTotalAmount(paramMap);
	}
	
	public JSONArray getPageList(Map<String, Object> paramMap) {
		return bookDAO.selectPageList(paramMap);
	}
	
	public int checkOutBook(Map<String, Object> paramMap) {
		int checkOutResult = bookDAO.updateBookStatusCheckout(paramMap);
		int addRentalResult  = bookDAO.insertNewRental(paramMap);
		return checkOutResult * addRentalResult;
	}
	
	public int reserveBook(Map<String, Object> paramMap) {
		int rentalID = bookDAO.selectNextRentalID(paramMap);
		paramMap.put("rentalID", rentalID);
		int reserveResult = bookDAO.insertWaitingRental(paramMap);
		return reserveResult;
	}
	
	public JSONArray getCheckoutList(Map<String, Object> paramMap) {
		return bookDAO.selectCheckoutList(paramMap);
	}
	
	public int returnBook(Map<String, Object> paramMap) {
		int updateRentalStatusReturnResult = bookDAO.updateRentalStatusReturn(paramMap);
		int queue = bookDAO.selectQueue(paramMap);
		paramMap.put("queue", queue);
		int updateBookStatusReturnResult = bookDAO.updateBookStatusReturn(paramMap);
		return updateRentalStatusReturnResult * updateBookStatusReturnResult;
	}
	
	public int confirmBook(Map<String, Object> paramMap) {
		int updateRentalStatusConfirmResult = bookDAO.updateRentalStatusConfirm(paramMap);
		List<Integer> queueList = bookDAO.selectQueueList(paramMap);
		int updateWaitingRentalResult = 0;
		for(int i = 0; i < queueList.size(); i++) {
			paramMap.put("NEW_QUEUE", (i+1));
			paramMap.put("OLD_QUEUE", queueList.get(i));
			updateWaitingRentalResult += bookDAO.updateWaitingRental(paramMap);
		}
		int updateBookStatusConfirmResult = bookDAO.updateBookStatusConfirm(paramMap);
		return updateRentalStatusConfirmResult * updateWaitingRentalResult * updateBookStatusConfirmResult;
	}
	
	public JSONArray loadReservation(Map<String, Object> paramMap) {
		return bookDAO.selectReservation(paramMap);
	}
	
	public int cancelReservation(Map<String, Object> paramMap) {
		int updateRentalStatusTerminatedResult = bookDAO.updateRentalStatusTerminated(paramMap);
		List<Integer> queueList = bookDAO.selectQueueList(paramMap);
		int updateWaitingRentalResult = 0;
		for(int i = 0; i < queueList.size(); i++) {
			paramMap.put("NEW_QUEUE", (i+1));
			paramMap.put("OLD_QUEUE", queueList.get(i));
			updateWaitingRentalResult += bookDAO.updateWaitingRental(paramMap);
		}
		return updateRentalStatusTerminatedResult * updateWaitingRentalResult;
	}
}
