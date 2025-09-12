package com.example.libraryManagement.in.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowedbooks")
public class BorrwedBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int borrowID;
	
	@ManyToOne
	@JoinColumn(name = "userId",nullable = false)
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name="bookId", nullable = false)
	private Book book;
	
	
	public BorrwedBook(){}
	
	public BorrwedBook(User user,Book book) {
		// TODO Auto-generated constructor stub
		this.user=user;
		this.book=book;
	}


	public int getBorrowID() {
		return borrowID;
	}


	public void setBorrowID(int borrowID) {
		this.borrowID = borrowID;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
