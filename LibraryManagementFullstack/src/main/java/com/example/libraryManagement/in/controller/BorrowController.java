package com.example.libraryManagement.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.in.dto.BorrowRequest;
import com.example.libraryManagement.in.entites.Book;
import com.example.libraryManagement.in.entites.BorrwedBook;
import com.example.libraryManagement.in.service.BorrowedBookService;

@RestController
@RequestMapping("/api/borrow")
@CrossOrigin(origins = "http://localhost:5173")
public class BorrowController {
	@Autowired
	private BorrowedBookService borrowService;
	
	@PostMapping("/bookborrow")
	public ResponseEntity<String> borrowBook(@RequestBody BorrowRequest borrowRequest)
	{
		System.out.println("come in borrow Controller");
		
		System.out.println("userID : "+borrowRequest.getUserId()+" bookId: "+borrowRequest.getBookId());
		boolean borrowed=borrowService.BorrowBook(borrowRequest.getBookId(), borrowRequest.getUserId());
				
		System.out.println("after boolean response");
		if(borrowed==true)
		{
			System.out.println("borrowed successfullt");
			return ResponseEntity.ok("Book Borrowed Successfully");
		}
		System.out.println("can't borrowed");
		return ResponseEntity.status(401).body("Failed");
	}
	
	
	@GetMapping("/{id}")
	public List<BorrwedBook> getAllBorrowed(@PathVariable int id)
	{
		return borrowService.MyBorrowedBooks(id);
	}
	
	
	@GetMapping("/history/{id}")
	public List<BorrwedBook> getHistory(@PathVariable int id)
	{
		return borrowService.getAllHistory(id);
	}
	
	@GetMapping("/return")
	public ResponseEntity<String> Returnborrow(@RequestParam int userId, @RequestParam int bookId)
	{
		boolean returned = borrowService.ReturnBorrowedBookByUserIdAndBookId(userId, bookId);
        if (returned) {
            return ResponseEntity.ok("Book Returned");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
        }

	}
}
