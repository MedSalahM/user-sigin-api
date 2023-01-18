package com.mms.users;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mms.users.domain.AppUser;
import com.mms.users.helper.AppUserRowMapper;

@SpringBootApplication
public class UserSignInApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(UserSignInApplication.class);
	
	
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	 
	 
	public static void main(String[] args) {
		
		SpringApplication.run(UserSignInApplication.class, args);
		
		
	     
		
		
	}


	@Override
	public void run(String... args) throws Exception {

		 AppUserRowMapper mapper = new AppUserRowMapper();

		 log.info("Creating tables");

		    jdbcTemplate.execute("DROP TABLE users IF EXISTS");
		    jdbcTemplate.execute("CREATE TABLE users(" +
		        "id  serial, username VARCHAR(255), password VARCHAR(255), role VARCHAR(255),region VARCHAR(255))"
		        );
		    
		
		    
		    // Split up the array of whole names into an array of first/last names
		    List<Object[]> splitUpNames = Arrays.asList("RAMI 1234 admin TEBESSA", "TAKI 1234 user ALGER", "HAMDI 0000 user ANNABA").stream()
		        .map(name -> name.split(" "))
		        .collect(Collectors.toList());

		 
		    // Use a Java 8 stream to print out each tuple of the list
		    splitUpNames.forEach(name -> log.info(String.format("Inserting user record for %s %s %s", name[0], "****" , name[3])));

		    // Uses JdbcTemplate's batchUpdate operation to bulk load data
		    jdbcTemplate.batchUpdate("INSERT INTO users(username, password,role,region) VALUES (?,?,?,?)", splitUpNames);

	
		    
		    
		    
	}
	
	
	
	
	
	
}
