package com.blogApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.payloads.UserDto;
import com.blogApp.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("")
	public ResponseEntity<UserDto> createUser( @RequestBody UserDto userdto){
UserDto createUserDto = 	this.userservice.createUser(userdto);
	return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	} 
}
