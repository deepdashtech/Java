package com.example.libraryManagement.in.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libraryManagement.in.entites.Book;
import com.example.libraryManagement.in.repository.bookRepository;

@Service
public class BookService {
	
	Scanner sc= new Scanner(System.in);
	
	@Autowired
	private bookRepository bookRepo;
	
	public Boolean SaveBook(String title,String isbn,int copies,String author,String filepath)
	{
		Book books=bookRepo.findByIsbn(isbn);
		if (books != null) {
			return false;
		}
		
		bookRepo.save(new Book(title, isbn, copies,author,filepath));
		return true;
	}
	
	public Book SearchBookById(int id)
	{
		return bookRepo.findByBookId(id);
	}
	
	public List<Book> GetAllBooks()
	{
		return bookRepo.findAll();
	}
	
	public void DisplayAllBook()
	{
		List<Book> books=GetAllBooks();
		
		for (int i = 0; i < books.size(); i++) {
			System.out.println("Book: "+i);
			System.out.println("\nTitle: "+books.get(i).getTitle());
			System.out.println("\nISBN: "+books.get(i).getIsbn());
			System.out.println("\nCopies Available: "+books.get(i).getNumberOfCopies());
		}
	}
	
	public Optional<Book> GetBook(Integer id)
	{
		return bookRepo.findById(id);
	}
	
	public void DisplayBook()
	{
			Book book=SearchBookByTitle();
			System.out.println("\nTitle: "+book.getTitle());
			System.out.println("\nISBN: "+book.getIsbn());
			System.out.println("\nCopies Available: "+book.getNumberOfCopies());
	}
	
	public boolean DeleteBook(int id)
	{
		Optional<Book> book=bookRepo.findById(id);
		if (book.isEmpty()) 
		{
			return false;
		}
		bookRepo.delete(book.get());
		return true;
	}
	
	public Book SearchBookByTitle()
	{
		System.out.println("Enter Book Title: ");
		String title=sc.nextLine();
		return bookRepo.findByTitle(title);
	}
	
	public boolean EditBook(int id,String title,String isbn,int copies)
	{
		
		Book book=bookRepo.findByBookId(id);
		
		if(book != null)
		{
			
			book.setTitle(title);
			book.setIsbn(isbn);
			book.setNumberOfCopies(copies);
	
			bookRepo.save(book);
			return true;
		}

		return false;
	}
	
	
	public Book GetBook()
	{
		System.out.println("Enter ISBN: ");
		String isbn=sc.next();
		return bookRepo.findByIsbn(isbn);
	}
	
//	public Book BorrowBook()
//	{
		
//		Book book=availableBook();
//		if (book==null) {
//			return null;
//		}
//		book.setNumberOfCopies(book.getNumberOfCopies()-1);
//		return book;
//	}
	
	public void DisplayBook(Book book)
	{
		System.out.println("\nTitle: "+book.getTitle());
		System.out.println("\nISBN: "+book.getIsbn());
	}
	
	public boolean checkBookQuantity(Book book)
	{
		if (book.getNumberOfCopies()>0) {
			return true;
		}
		return false;
	}

	public void BorrowupdateQuantity(Book book)
	{
		book.setNumberOfCopies(book.getNumberOfCopies()-1);
		bookRepo.save(book);
	}
	
	public void ReturnupdateQuantity(Book book)
	{
		book.setNumberOfCopies(book.getNumberOfCopies()+1);
		bookRepo.save(book);
	}

	
//	public Book FindBookByISBN()
//	{}
	
	
//	public Book ReturnBook()
//	{}
	
	
}
