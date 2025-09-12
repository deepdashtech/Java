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
		
		List<BorrwedBook> findByUser_UserId(int userId); 
		List<BorrwedBook> findByUserAndBook(User user, Book book);
		List<BorrwedBook> findByUser_UserIdAndBook_BookId(int userId, int bookId);
		
		 @Transactional
		    @Modifying
		    @Query("DELETE FROM BorrwedBook bb WHERE bb.user.userId = :userId AND bb.book.bookId = :bookId")
		    int deleteByUserIdAndBookId(@Param("userId") int userId, @Param("bookId") int bookId);
		
		List<BorrwedBook> findByUserId(int userId);
}
