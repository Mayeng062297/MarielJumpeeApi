package com.jumpeeApi.springprojectapi.configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jumpeeApi.springprojectapi.model.User;
import com.jumpeeApi.springprojectapi.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	
//Implement this method user by username
/*
	this request will contain the basic auth that will be username and password. and this userdetails will recieve
	that username, based on this username we can query and get the user from database
*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
	//finding username in database, if null throw an exception "user not found" otherwise return the user details implementation
	User inDB = repository.findByUsername(username);
	if (inDB == null) {
		throw new UsernameNotFoundException("User Not Found");
	}
		return new AppUser(inDB);
	}

}

