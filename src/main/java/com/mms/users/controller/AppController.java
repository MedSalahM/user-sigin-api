package com.mms.users.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mms.users.domain.AppUser;
import com.mms.users.domain.SystemUser;
import com.mms.users.service.SystemUserService;
import com.mms.users.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class AppController {
	
	private final SystemUserService systemUserService;
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
	
	
	@PutMapping("user")
	public int update(@RequestBody AppUser user) {
		
		
		return userServiceImpl.editUser(user);
		
	}
	
	@GetMapping("user/username")
	public List<String> usernamesByRegion(@RequestParam Optional<String> region){
		
		
		
		String find = region.orElse("");
		
		return userServiceImpl.userNamesByRegion(find);
		
		
	}
	
	@GetMapping("sy/all")
	public List<SystemUser> sysUsers() throws Exception{
		
		return systemUserService.getAllSystemUsers();
		
		
	}
	
	@PostMapping("ps/command")
	
	public ResponseEntity<String> powerShellCommand(@RequestBody String command) throws Exception {
		
		return  ResponseEntity.ok(systemUserService.commandeExecution(command)) ;
		
	}

}
