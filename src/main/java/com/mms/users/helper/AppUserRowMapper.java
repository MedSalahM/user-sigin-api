package com.mms.users.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mms.users.domain.AppUser;

public class AppUserRowMapper implements  RowMapper<AppUser>{

	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

          AppUser user = new AppUser();
          user.setPassword(rs.getString("password"));
          user.setUsername(rs.getString("username"));
          user.setRole(rs.getString("role"));
          user.setId(rs.getInt("id"));
          user.setRegion(rs.getString("code_commune"));
  
          return user;
		
	}

}