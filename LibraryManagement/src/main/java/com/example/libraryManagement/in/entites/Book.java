package com.example.libraryManagement.in.entites;

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
	int bookId;
	
	@Column
	String title;
	
	@Column
	String isbn;
	
	@Column
	int NumberOfCopies;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String title, String isbn, int NumberOfCopies) {
		
		
		this.isbn=isbn;
		this.title=title;
		this.NumberOfCopies=NumberOfCopies;
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return bookId;
	}

	public void setId(int id) {
		this.bookId = id;
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
	
	
	
}
