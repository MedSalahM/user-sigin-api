package com.mms.users.service;

import java.util.ArrayList;
import java.util.List;

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
	
	
    
    public List<AppUser> users(){
    	
    	
    	var users = new ArrayList<AppUser>();
    	
    	userRepo.allUsers().forEach(users::add);
    	
    	return users;
    	
    	
    }
    
	 public List<String> regions(){
		 
		 List<String> regions = new ArrayList<>();
		 
		 this.users().stream().map(AppUser::getRegion)
		 .forEach(regions::add);
		 
		 return regions;
		 
	 }
	 
	 
	 public int editUser(AppUser user)
	 {
		 
		 return userRepo.update(user);
		 
		 
	 }
    
	 public List<String> userNamesByRegion(String region){

		 List<String> usernames = new ArrayList<>();
		 
		 users()
		 .stream()
		 .filter(reg->reg.getRegion().equals(region))
		 .map(AppUser::getUsername)
		 .forEach(usernames::add);
		 
		 
		 return usernames;
		 
	 }
	 
    
}
