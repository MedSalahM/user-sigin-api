package com.mms.users.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mms.users.domain.AppUser;
import com.mms.users.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

	
	private final UserRepository userRepo;
	
    public AppUser	findByUsername(String username){
		
    	if(username==null) {
    		
    		throw new UsernameNotFoundException("user not found");
    	
    	}

    	
    	  AppUser user= userRepo.findByUsername(username)
    	    		    .orElseThrow(()-> new UsernameNotFoundException(username));
    	  
    	  return user;
	}
	
	
}
