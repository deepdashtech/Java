package com.example.libraryManagement;


import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.AssertingParty.Verification;

import com.example.libraryManagement.in.entites.User;
import com.example.libraryManagement.in.service.BookService;
import com.example.libraryManagement.in.service.LibraryService;
import com.example.libraryManagement.in.service.UserService;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) 
	{
		
		var context= SpringApplication.run(LibraryManagementApplication.class, args);
		
//		UserService uservice=context.getBean(UserService.class);
//		BookService bservice=context.getBean(BookService.class);
		
		LibraryService libraryService=context.getBean(LibraryService.class);
		
		VerificationMenu(libraryService);
		
		
//		libraryService.AddBook();
//		libraryService.AddUser();
		
//		libraryService.BorrowBook();
		
//		libraryService.DisplayAllBook();
		
//		libraryService.DisplayBook(1);
		
//		uservice.SaveUser();
//		bservice.SaveBook();
		
//		System.out.println("Hello");
//		
//		Configuration cfg=new Configuration();
//		cfg.configure("in/config/hibernate.cfg.xml");
//		
//		SessionFactory sessionfactory=cfg.buildSessionFactory();
//		Session session=sessionfactory.openSession();
//		Transaction transaction=session.beginTransaction();
//		
//		User user1=new User("Om","password","Admin");
//		
//		session.save(user1);
//		transaction.commit();	
//		
//		System.out.println("Addedd Successfully!!");	
	
	
		
	
	
	
	
	}
	
	public static void VerificationMenu(LibraryService libraryService)
	{
		System.out.println("\n\n-------- Library Management --------\n\n"+//
				"1. Login\n"+//
				"2. Sign Up\n"+//
				"3. Exit");
		
		Scanner sc=new Scanner(System.in);
		int res=sc.nextInt();
		
		switch (res) {
		case 1:
			String usertype=libraryService.LoginUser();
			if (usertype.equalsIgnoreCase("user")) {
				UserOperations(libraryService);
			}
			else if(usertype.equalsIgnoreCase("admin")) {
				AdminOperations(libraryService);
			}
			else {
				VerificationMenu(libraryService);
			}
			break;
			
			
		case 2:
			libraryService.AddUser();
			break;
			
		case 3:
			return;
			
		default:
			VerificationMenu(libraryService);
			break;
		}
		VerificationMenu(libraryService);
	}
	
	public static void UserOperations(LibraryService libraryService)
	{
		System.out.println("\n\n-------- User Operations --------\n\n"+//
				"1. View All Books\n"+//
				"2. Borrow Book\n"+//
				"3. Return Book\n"+//
				"4. Serach Book By Title\n"+//
				"5. My Borrowed Book\n"+//
				"6. Log Out");
		
		Scanner sc=new Scanner(System.in);
		int res=sc.nextInt();

		switch (res) {
		case 1:
			libraryService.DisplayAllBook();
			break;


		case 2:
			libraryService.BorrowBook();	
			break;


		case 3:
			libraryService.ReturnBook();
			break;


		case 4:
			libraryService.SearchBookByTitle();
			break;


		case 5:
			libraryService.MyBorrowedBooks();
			break;


		case 6:
			libraryService.LogOutUser();
			VerificationMenu(libraryService);
			break;

		default:
			UserOperations(libraryService);
			break;
		}
		UserOperations(libraryService);
	}
	
	public static void AdminOperations(LibraryService libraryService)
	{
		System.out.println("\n\n-------- Admin Operations --------\n\n"+//
				"1. Add Book\n"+//
				"2. Delete Book\n"+//
				"3. List All Books\n"+//
				"4. Edit Books(ISBN)\n"+//
				"5. Show User List \n"+//
				"6. Log Out");
		
		Scanner sc=new Scanner(System.in);
		int res=sc.nextInt();
		
		switch (res) {
		case 1:
			libraryService.AddBook();
			break;

		case 2:
			libraryService.DeleteBook();
			break;

		case 3:
			libraryService.DisplayAllBook();
			break;

		case 4:
			libraryService.EditBook();
			break;

		case 5:
			libraryService.DisplayAllUsers();
			break;

		case 6:
			libraryService.LogOutUser();
			VerificationMenu(libraryService);
			break;

		default:
			AdminOperations(libraryService);
			break;
		}
		AdminOperations(libraryService);
	}
}
