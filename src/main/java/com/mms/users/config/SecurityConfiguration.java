package com.mms.users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mms.users.filter.CustomAuhenticationFilter;
import com.mms.users.filter.CustomAuthorizationFilter;
import com.mms.users.security.CustomAuthenticationManager;
import com.mms.users.service.MyUserDetailsService;
import com.mms.users.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
	
//	private final MyUserDetailsService userDetailsService;
	
	@Autowired
	CustomAuthorizationFilter authFilter;
	
	@Autowired
	CustomAuthenticationManager customAuth;
	

	
	@Bean
	public PasswordEncoder pe() {
		
		//return NoOpPasswordEncoder.getInstance();
		
		BCryptPasswordEncoder be  = new BCryptPasswordEncoder(10);
		
		return be;
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		CustomAuhenticationFilter cf = new CustomAuhenticationFilter(customAuth);
        cf.setFilterProcessesUrl("/api/login");
       
		
         http.csrf().disable();
		 http.cors(Customizer.withDefaults());
		 http.authorizeHttpRequests(authz->{
			                                  authz.requestMatchers("/api/user/**","/api/region","/h2-console")
			                                 .permitAll()
			                                 .anyRequest().authenticated();
			                                
		                 	            }
		 
		                           );
		
		 
		 http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		 http.addFilter(cf);
	    
		 http.headers().frameOptions().disable();
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		 return http.build();
		
	}
	
	
	 
}


