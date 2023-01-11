package com.mms.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.mms.users.service.UserServiceImpl;

@Component
public class CustomAutnenticationProvider implements AuthenticationProvider {
	
	
	@Autowired
	UserServiceImpl userService;

	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
		
		var appUser = userService.findByUsername(authentication.getName());
		
		var pwd = authentication.getCredentials().toString();
		
		
		Integer region=appUser.getRegion().getId();
		
		
		
		
		Integer detail = Integer.parseInt(authentication.getDetails().toString());  
		
		if(! region.equals(detail)  ) {
			
			throw new BadCredentialsException("Region not compatible");
		}
		
	   if(! pwd.equals(appUser.getPassword())  ) {
			
			throw new BadCredentialsException("Password not correct");
		}
		
		

	   
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
	
}
