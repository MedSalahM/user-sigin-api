package com.mms.users.service;

import java.io.IOException;
import java.util.List;

import com.mms.users.domain.SystemUser;

public interface SystemUserService {

	

	
	
	List<SystemUser> getAllSystemUsers() throws Exception;
	
	String commandeExecution(String command) throws Exception;
	
}
