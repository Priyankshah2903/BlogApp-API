package com.priyank.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyank.api.payload.UserDto;
import com.priyank.api.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
   private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager ;
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto ){
		
		UserDto registerUserDto=this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(registerUserDto,HttpStatus.CREATED);
		
	}
	
	
}
