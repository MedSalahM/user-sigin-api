package com.mms.users.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mms.users.domain.AppUser;
import com.mms.users.security.CustomAuthenticationManager;
import com.mms.users.service.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAuhenticationFilter extends  UsernamePasswordAuthenticationFilter{
	
	private final CustomAuthenticationManager authMan;
	
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String regionId = request.getParameter("regionId");
 		
		
		UsernamePasswordAuthenticationToken auth = 
				 new UsernamePasswordAuthenticationToken(username, password);
	   
	
		auth.setDetails(regionId);
		
		Authentication authenticated = authMan.authenticate(auth);
	
		
		
		
		return authenticated;
		
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		

		
		
	     String user=  authResult.getName();
	
	   
	     
	     
	     List<String> s =  authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();


	     String[] str = new String[s.size()];
	     
	     for (int i = 0; i < s.size(); i++) {
	         str[i] = s.get(i);
	     }
	     
	     Algorithm algorithm =Algorithm.HMAC256("toyota".getBytes());
	     
		String access_token = JWT.create()
	    		                  .withSubject(user)
	    		                  .withExpiresAt(new Date(System.currentTimeMillis()+720*60*1000))
	    		                  .withIssuer(request.getRequestURL().toString())
	    		                  .withArrayClaim("roles", str)
	    		                  
	    		                  .sign(algorithm);
		
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("access_token", access_token);
		
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		
	}
}
