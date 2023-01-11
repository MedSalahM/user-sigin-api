package com.mms.users.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.mms.users.domain.AppUser;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final RegionRepo regionRepo;
	   
	List<AppUser> allUsers(){
		
		List<AppUser> users = new ArrayList<>();
		
		users.add(AppUser.builder()
				         .id(1)
				         .username("rami")
				         .password("1234")
				         .role("user")
				         .region(regionRepo.getByid(1))
				         .build());
		
		users.add(AppUser.builder()
		         .id(1)
		         .username("taki")
		         .password("1234")
		         .role("user")
		         .region(regionRepo.getByid(2))
		         .build());
		
		users.add(AppUser.builder()
		         .id(2)
		         .username("hamdi")
		         .password("1234")
		         .role("user")
		         .region(regionRepo.getByid(2))
		         .build());
		
		return users;
		
		
		
	}
	
 
	 public Optional<AppUser> findByUsername(String username){
		 
		    return allUsers().stream()
		    .filter(r->r.getUsername().equals(username))
		    .findFirst();
	 }
	
	
}
