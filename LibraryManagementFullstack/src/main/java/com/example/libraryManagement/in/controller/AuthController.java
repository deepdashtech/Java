package com.example.libraryManagement.in.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.in.dto.LoginRequest;
import com.example.libraryManagement.in.dto.SignupRequest;
import com.example.libraryManagement.in.entites.User;
import com.example.libraryManagement.in.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
	{
		var user=userService.LoginUser(loginRequest.getUsername(), loginRequest.getPassword(),loginRequest.getUsertype());
		
		if (user != null) 
		{
			return ResponseEntity.ok(Map.of("success", true,"user", user));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("success", false, "message", "Not authorized!!"));
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest)
	{
		var user=userService.RegisterUser(signupRequest.getUsername(),signupRequest.getPassword(),signupRequest.getUsertype());
		if (user != null) 
		{
			return ResponseEntity.ok(Map.of("success", true,"user", user));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Map.of("success", false, "message", "Not Registeres Successfully!!"));
		}
	}
}
