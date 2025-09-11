package com.example.libraryManagement.in.entites;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String userType;
	
	@Column
	private int booksBorrowed=0;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<BorrwedBook> borroredBooks;

	public User() {}
	

	public User(String username,String password,String usertype) {
		this.userName=username;
		this.password=password;
		this.userType=usertype;
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {return userId;}
	public String getPassword() {return password;}
	public String getUsername() {return userName;}
	public String getUserType() {return userType;}
	public int getTotalBorrowed() {return booksBorrowed;}
	
	
	public void SetTotalBorrowed(int books) {booksBorrowed=books;}
}
