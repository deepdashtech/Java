package com.example.libraryManagement.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.libraryManagement.in.entites.Book;
import com.example.libraryManagement.in.entites.BorrwedBook;
import com.example.libraryManagement.in.entites.User;

import jakarta.transaction.Transactional;

@Repository
public interface borrowedBookRepository extends JpaRepository<BorrwedBook, Integer>{
//		List<BorrwedBook> findByUser(User user);
		
//		List<BorrwedBook> findByUser_UserId(int userId); 
		BorrwedBook findByUserAndBook(User user, Book book);
		BorrwedBook findByUser_UserIdAndBook_BookIdAndReturnDateIsNull(int userId, int bookId);
		
		List<BorrwedBook> findByUserId(int userId);
}
