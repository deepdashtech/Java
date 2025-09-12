package com.example.libraryManagement.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libraryManagement.in.entites.Book;

@Repository
public interface bookRepository extends JpaRepository<Book, Integer> {
	Book findByIsbn(String isbn);
	Book findByTitle(String title);
	Book findByBookId(int bookId);
}
