package com.example.libraryManagement.in.entites;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
	
	
	@Column(nullable = false,updatable = false)
	@CreationTimestamp
	private LocalDateTime issueDate;
	
	@Column(nullable = true)
	private LocalDateTime returnDate;
	
	@Column(nullable = true)
	private LocalDateTime dueDate;
	
	
	@PrePersist
	public void onCreate() {
		if (issueDate==null) {
			issueDate=LocalDateTime.now();
		}
		dueDate=issueDate.plusDays(15);
	}
	
	@Column
	private int fineAmount=0;
	
	public BorrwedBook(){}
	
	public BorrwedBook(User user,Book book) {
		// TODO Auto-generated constructor stub
		this.user=user;
		this.book=book;
	}
	
	
	


	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public int getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
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
