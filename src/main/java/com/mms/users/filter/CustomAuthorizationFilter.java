package com.mms.users.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		String p = request.getServletPath();
		
		boolean path = p.equals("/api/region") || p.equals("/api/login") || p.equals("/api/user/username");
		
		return  path;
		
		
		
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	  
		
		
		
		
		
		String username =null;
		String token = null;
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		
	       if(header==null || header.isEmpty()  || !header.startsWith("Bearer ")) {
	    	
			   filterChain.doFilter(request, response);
			   
               return ;
	    	
	     }
	    

		token = header.split(" ")[1].trim(); 
		
	     Algorithm algo = Algorithm.HMAC256("toyota".getBytes());
		 
		 JWTVerifier verifier = JWT.require(algo).build();
		 DecodedJWT decodedJwt = verifier.verify(token);
		 username = decodedJwt.getSubject();
		

		 String roles []= decodedJwt.getClaim("roles").asArray(String.class);
		 
		 List<SimpleGrantedAuthority> auths = 
				 
				 Arrays.asList(roles).stream().map(SimpleGrantedAuthority::new)
				 .collect(Collectors.toList());
		 
		 
		 
		     UsernamePasswordAuthenticationToken 
	
		     authentication = new UsernamePasswordAuthenticationToken(username,null,auths );
		  
		     SecurityContextHolder.getContext().setAuthentication(authentication);
		    
		
	
		filterChain.doFilter(request, response);
		
	     
		
	
	}
	
}
