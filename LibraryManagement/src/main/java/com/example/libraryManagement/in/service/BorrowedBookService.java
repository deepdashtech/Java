package com.example.libraryManagement.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libraryManagement.in.entites.Book;
import com.example.libraryManagement.in.entites.BorrwedBook;
import com.example.libraryManagement.in.entites.User;
import com.example.libraryManagement.in.repository.borrowedBookRepository;

@Service
public class BorrowedBookService {
	
	@Autowired
	private borrowedBookRepository borrowRepo;
	
	public void SaveBorrow(User u, Book b)
	{
		borrowRepo.save(new BorrwedBook(u, b));
	}
	
	public void SaveReturn(List<BorrwedBook> borrowed)
	{
		borrowRepo.deleteAll(borrowed);
	}
	
	public List<BorrwedBook> MyBorrowedBooks(User user)
	{
		return borrowRepo.findByUser(user);
	}
	
	public List<BorrwedBook> checkBorrow(Book book, User user)
	{
		return borrowRepo.findByUserAndBook(user, book);
	}
}
