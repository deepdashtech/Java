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

import com.example.libraryManagement.in.dto.ApiResponse;
import com.example.libraryManagement.in.dto.LoginRequest;
import com.example.libraryManagement.in.dto.UserRequest;
import com.example.libraryManagement.in.dto.UserResponse;
import com.example.libraryManagement.in.entites.User;
import com.example.libraryManagement.in.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest loginRequest)
	{
		User user=userService.LoginUser(loginRequest.getUsername(), loginRequest.getPassword(),loginRequest.getUsertype());
		
		if (user != null) 
		{
			UserResponse userResp=new UserResponse(user.getUserId(),user.getUsername(),user.getUserType());
			ApiResponse<UserResponse> response=new ApiResponse<UserResponse>("success","Login Successful!!", userResp);
			return ResponseEntity.ok(response);
		}
		else 
		{
			ApiResponse<UserResponse> response=new ApiResponse<UserResponse>("failed","Not Authorized!!", null);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<UserResponse>> signup(@RequestBody UserRequest signupRequest)
	{
		User user=userService.RegisterUser(signupRequest.getUsername(),signupRequest.getPassword(),signupRequest.getUsertype());
		if (user != null) 
		{
			UserResponse userResp=new UserResponse(user.getUserId(),user.getUsername(),user.getUserType());
			ApiResponse<UserResponse> response=new ApiResponse<UserResponse>("success","Register Successful!!", userResp);
			return ResponseEntity.ok(response);
		}
		else {
			ApiResponse<UserResponse> response=new ApiResponse<UserResponse>("failed","Failed To Register", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
