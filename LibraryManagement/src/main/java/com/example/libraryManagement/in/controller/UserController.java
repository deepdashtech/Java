package com.example.libraryManagement.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.in.entites.User;
import com.example.libraryManagement.in.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
//	@GetMapping
//	public List<User> getAllUsers()
//	{
//		return userService.DisplayAllUsers();
//	}
}
