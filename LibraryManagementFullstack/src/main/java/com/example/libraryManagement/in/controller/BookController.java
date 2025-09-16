package com.example.libraryManagement.in.controller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.libraryManagement.in.dto.BookRequest;
import com.example.libraryManagement.in.entites.Book;
import com.example.libraryManagement.in.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/all")
	public List<Book> getAllBooks()
	{
		return bookService.GetAllBooks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id)
	{
		Book book=bookService.SearchBookById(id);
		
		if (book!=null) {
			return ResponseEntity.ok(book);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> editBook(@PathVariable int id,@RequestBody BookRequest bookRequest)
	{
		boolean updated=bookService.EditBook(id,bookRequest.getTitle(),bookRequest.getIsbn(),bookRequest.getNumberOfCopies());
		
		if(updated)
		{
			return ResponseEntity.ok("Updated Successfully!!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found");
	}
	
	
	@PostMapping(value =  "/add", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> addBook(@RequestPart("book") BookRequest bookrequest,
	                                      @RequestPart("image") MultipartFile imagefile) {
	    String fileName = null;
	    try {
	        String uploadDir = "uploads/books/";
	        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        String originalFilename = imagefile.getOriginalFilename();
	        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	        fileName = "Book_ISBN_" +bookrequest.getIsbn()+ extension;

	        Path filePath = uploadPath.resolve(fileName);
	        imagefile.transferTo(filePath.toFile());

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Failed to upload image: " + e.getMessage());
	    }

	    boolean added = bookService.SaveBook(
	            bookrequest.getTitle(),
	            bookrequest.getIsbn(),
	            bookrequest.getNumberOfCopies(),
	            bookrequest.getAuthor(),
	            "/uploads/books/" + fileName);

	    if (added) {
	        return ResponseEntity.ok("Added Successfully");
	    }
	    return ResponseEntity.status(HttpStatus.CONFLICT).body("Book Already Exists");
	}


	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id)
	{
		boolean deleted=bookService.DeleteBook(id);
		if (deleted) {
			return ResponseEntity.ok("Book Deleted Successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found");
		
	}
}
