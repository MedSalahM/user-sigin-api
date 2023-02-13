package com.mms.users.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mms.users.exception.InvalidCommandException;

@RestControllerAdvice
public class AppControllerAdvice {

	
	@ExceptionHandler(InvalidCommandException.class)
	public ResponseEntity<ProblemDetail> handlInvalidCommand(InvalidCommandException ex) {
		
		 var a =	HttpStatus.INTERNAL_SERVER_ERROR;
			
			ProblemDetail apiError = ProblemDetail.forStatusAndDetail(a, ex.getMessage()) ;
			
			return new ResponseEntity<ProblemDetail>(apiError ,a );

		
		
	}
	
	
	
}
