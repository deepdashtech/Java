package com.example.libraryManagement.in.dto;

public class BorrowRequest {
	private int bookId;
	private int userId;
	
	
	
	public BorrowRequest(int bookId, int userId) {
		super();
		this.bookId = bookId;
		this.userId = userId;
	}
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
