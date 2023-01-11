package com.mms.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private CustomAutnenticationProvider provider;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			
		return provider.authenticate(authentication);
	
	}

	
}
