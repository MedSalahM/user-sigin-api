package com.mms.users.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.mms.users.domain.AppUser;
import com.mms.users.helper.AppUserRowMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	 private  AppUserRowMapper mapper = new AppUserRowMapper();

	
	public List<AppUser> allUsers(){
		
		
		var users = jdbcTemplate.query("select * from app_user", mapper);
	
		
		return users;
		
		
		
	}
	
 
	 public Optional<AppUser> findByUsername(String username){
		 
		    return allUsers().stream()
		    .filter(r->r.getUsername().equals(username))
		    .findFirst();
	 }
	 
	 
	 public int update(AppUser user){
		 
		 
		 
		 String sqlUpdateUser="update app_user set username = ? , password = ? where id = ?";
			
			
			
			
			Object values []= { user.getUsername(),user.getPassword() , user.getId()} ;
			
			int resultAffectation = jdbcTemplate.update(sqlUpdateUser, values);
			
			
		 return resultAffectation;
	 }
	 
	
	 
	
}
