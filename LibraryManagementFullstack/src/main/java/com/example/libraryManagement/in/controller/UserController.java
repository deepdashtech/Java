package com.example.libraryManagement.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.in.entites.User;
import com.example.libraryManagement.in.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<User> getAllUsers()
	{
		return userService.DisplayAllUsers();
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username)
	{
		User user=userService.FindByUsername(username);
		
		user.setPassword(null);
		
		if(user !=null)
		{
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/userid/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id)
	{
		User user=userService.FindByUserId(id);
		
		user.setPassword(null);
		
		if(user !=null)
		{
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
