package com.example.libraryManagement.in.entites;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@Column
	private String title;
	
	@Column
	private String isbn;
	
	@Column
	private int NumberOfCopies;
	
	@Column
	private int totalCopies;
	

	@Column
	private String author;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	
	@Column
	private String imagePath;	
	

	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String title, String isbn, int NumberOfCopies,String author) {
		
		this.isbn=isbn;
		this.title=title;
		this.NumberOfCopies=NumberOfCopies;
		this.author=author;
		totalCopies=NumberOfCopies;
		// TODO Auto-generated constructor stub
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNumberOfCopies() {
		return NumberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		NumberOfCopies = numberOfCopies;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	public int getTotalCopies() {
		return totalCopies;
	}
	
	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
