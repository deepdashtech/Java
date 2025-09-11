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
	
	public Book SaveBook()
	{
		System.out.print("Title:");
		String title=sc.nextLine();
		
		System.out.print("ISBN:");
		String isbn=sc.nextLine();
		
		System.out.print("Number OF Copies:");
		int copies=sc.nextInt();
		
		return bookRepo.save(new Book(title, isbn, copies));
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
	
	public void DeleteBook()
	{
		Book book=SearchBookByTitle();
		if (book == null) {
			System.out.println("No Books Found");
		}
		bookRepo.delete(book);
	}
	
	public Book SearchBookByTitle()
	{
		System.out.println("Enter Book Title: ");
		String title=sc.nextLine();
		return bookRepo.findByTitle(title);
	}
	
	public void EditBook()
	{
		Book book=SearchBookByTitle();
		if (book == null) {
			System.out.println("No Books Found");
		}
		System.out.println("Current Data\n\n");
		System.out.println("\nTitle: "+book.getTitle());
		System.out.println("\nISBN: "+book.getIsbn());
		System.out.println("\nCopies Available: "+book.getNumberOfCopies());
		
		
		System.out.println("Enter Title");
		book.setTitle(sc.nextLine());
		
		System.out.println("Enter ISBN");
		book.setIsbn(sc.nextLine());
		
		System.out.println("Enter Copies Available");
		book.setNumberOfCopies(sc.nextInt());
		
		bookRepo.save(book);
		
		System.out.println("Updated Successfully");
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
