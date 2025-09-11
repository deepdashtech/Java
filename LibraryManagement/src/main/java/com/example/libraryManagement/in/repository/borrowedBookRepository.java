package com.example.libraryManagement.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libraryManagement.in.entites.Book;
import com.example.libraryManagement.in.entites.BorrwedBook;
import com.example.libraryManagement.in.entites.User;

@Repository
public interface borrowedBookRepository extends JpaRepository<BorrwedBook, Integer>{
		List<BorrwedBook> findByUser(User user);
		List<BorrwedBook> findByUserAndBook(User user, Book book);
}
