package com.mms.users.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mms.users.domain.AppUser;

import com.mms.users.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class AppController {
	
	
	private final UserServiceImpl userServiceImpl;

	
	
	
	
	@GetMapping("home")
	public ResponseEntity<?> home(Principal principal) {
		
		   
		
		     var username = principal.getName();
		     var user = userServiceImpl.findByUsername(username);
		     
		
		
		return  ResponseEntity.ok(user);
	}
	
	
	@GetMapping("region")
	public List<String> regions(){
		
		return userServiceImpl.regions();
		
		
	}
	
	@GetMapping("user")
	public List<AppUser> users(){
		
		return userServiceImpl.users();
		
		
	}
	

}
