package com.mms.users.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mms.users.domain.SystemUser;
import com.mms.users.exception.InvalidCommandException;

@Service
public class SystemServiceImpl implements SystemUserService {

	@Override
	public String commandeExecution(String command) throws Exception {

		System.out.println(command);
		
		String result = null;
		final ProcessBuilder pb = new ProcessBuilder("C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe",
				"-Command", command);

		final Process p = pb.start();
		
		int r = p.waitFor();
		
		System.out.println(r);

		if ( r== 0) {

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));

			}
			result = builder.toString();

		}

		else {

			
			throw new InvalidCommandException("commande non valide");
			
			
		}

		return result;
	}

	@Override
	public List<SystemUser> getAllSystemUsers() throws Exception {

		return null;
	}

}
