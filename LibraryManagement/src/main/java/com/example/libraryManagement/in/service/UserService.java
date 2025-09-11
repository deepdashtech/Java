package com.example.libraryManagement.in.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libraryManagement.in.entites.User;
import com.example.libraryManagement.in.repository.userRepository;

@Service
public class UserService {
	
	Scanner sc=new Scanner(System.in);
	
	@Autowired
	private userRepository userRepo;
	
	private User LoggedUser=null;
	
	
	public User getLoggedUser() {return LoggedUser;}
	
	
	public User RegisterUser()
	{
		System.out.println("Enter Name:");
		String usename=sc.nextLine();
		
		System.out.println("Enter Password:");
		String password=sc.nextLine();
		
		System.out.println("Enter usertype:");
		String usentype=sc.nextLine();
		
		User u1=new User(usename, password, usentype);
		
		return userRepo.save(u1);
	}
	
	public void DiplayUsers(User user)
	{
		System.out.println("\nUsername: "+user.getUsername());
		System.out.println("\nUser Type: "+user.getUserType());
		System.out.println("\nTotal Borrowed: "+user.getTotalBorrowed());
	}
	
	public void DisplayAllUsers()
	{
		List<User> users=userRepo.findAll();
		
		for (int i = 0; i < users.size(); i++) {
			System.out.println("User "+(i+1));
			
			System.out.println("\nUsername: "+users.get(i).getUsername());
			System.out.println("\nUser Type: "+users.get(i).getUserType());
			System.out.println("\nTotal Borrowed: "+users.get(i).getTotalBorrowed());
		}
	}
	
	public User FindByUsername()
	{
		System.out.println("Enter Username: ");
		String username= sc.nextLine();
		return userRepo.findByUserName(username);
	}
	
	
	public String LoginUser()
	{
		User u=VerifyUser();
		
		if (u == null) {
			return null;
		}
		LoggedUser=u;
		System.out.println("Logged Successfully");
		return u.getUserType();
	}
	
	public void LogOutUser() {
		LoggedUser=null;
	}
	
	
	public User VerifyUser()
	{
		System.out.println("Enter Username:");
		String username= sc.nextLine();
		
		User user=userRepo.findByUserName(username);
		
		if(user==null)
		{
			System.out.println("User Doesn't Exist");
			return null;
		}
		System.out.println("Enter Password: ");
		String password=sc.nextLine();
		System.out.println(password+"  "+user.getPassword());
		if ( user.getPassword().equals(password)) {
			return user;
		}
		System.out.println("Wrong Password");
		return null;
	}
	
	
	public void ReturnupdateQuantity()
	{
		LoggedUser.SetTotalBorrowed(LoggedUser.getTotalBorrowed()-1);
		userRepo.save(LoggedUser);
	}
	
	public void BorrowupdateQuantity()
	{
		LoggedUser.SetTotalBorrowed(LoggedUser.getTotalBorrowed()+1);
		userRepo.save(LoggedUser);
	}
}
