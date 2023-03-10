package com.mms.users.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class AppUser {

	private Integer id;
	private String username;
	private String password;
	private String role;
	private String region;
	
}
