package com.example.libraryManagement.in.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.libraryManagement.in.dto.ApiResponse;
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
	public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
	    List<Book> books = bookService.GetAllBooks();

	    ApiResponse<List<Book>> res = new ApiResponse<>(
	        "success",
	        "Books fetched successfully",
	        books
	    );

	    return ResponseEntity.ok(res);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Book>> getBook(@PathVariable int id)
	{
		Book book=bookService.SearchBookById(id);
		
		if (book!=null) {
			ApiResponse<Book> res=new ApiResponse<Book>("success", "Get Successfully", book);
			return ResponseEntity.ok(res);  
		}
		ApiResponse<Book> res = new ApiResponse<>("error", "Book Not Found", null);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<ApiResponse<String>> editBook(
	    @PathVariable int id,
	    @RequestBody BookRequest bookRequest) {

	    boolean updated = bookService.EditBook(
	        id,
	        bookRequest.getTitle(),
	        bookRequest.getIsbn(),
	        bookRequest.getNumberOfCopies());

	    if (updated) {
	        ApiResponse<String> res = new ApiResponse<>("success", "Updated Successfully!!", null);
	        return ResponseEntity.ok(res);
	    }

	    ApiResponse<String> res = new ApiResponse<>("error", "Book Not Found", null);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}

	
	
	@PostMapping(value = "/add", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse<String>> addBook(
	    @RequestPart("book") BookRequest bookrequest,
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
	        fileName = "Book_ISBN_" + bookrequest.getIsbn() + extension;

	        Path filePath = uploadPath.resolve(fileName);
	        imagefile.transferTo(filePath.toFile());

	    } catch (Exception e) {
	        e.printStackTrace();
	        ApiResponse<String> res = new ApiResponse<>(
	            "error",
	            "Failed to upload image: " + e.getMessage(),
	            null
	        );
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	    }

	    boolean added = bookService.SaveBook(
	        bookrequest.getTitle(),
	        bookrequest.getIsbn(),
	        bookrequest.getNumberOfCopies(),
	        bookrequest.getAuthor(),
	        "/uploads/books/" + fileName);

	    if (added) {
	        ApiResponse<String> res = new ApiResponse<>(
	            "success",
	            "Book added successfully",
	            null
	        );
	        return ResponseEntity.ok(res);
	    }

	    ApiResponse<String> res = new ApiResponse<>(
	        "error",
	        "Book Already Exists",
	        null
	    );
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
	}



	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable int id) {
	    boolean deleted = bookService.DeleteBook(id);

	    if (deleted) {
	        ApiResponse<String> res = new ApiResponse<>("success", "Book Deleted Successfully", null);
	        return ResponseEntity.ok(res);
	    }

	    ApiResponse<String> res = new ApiResponse<>("error", "Book Not Found", null);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}

}
