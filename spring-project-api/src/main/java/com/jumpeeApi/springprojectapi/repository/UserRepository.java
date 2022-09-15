package com.jumpeeApi.springprojectapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpeeApi.springprojectapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//QUERIES
	User findByEmail(String email);
	User findByUsername(String username);  //query for spring security username //method that finds that user by username 
	//username field on the Usermodel class
	User findByToken(String token); //for reset password token
	// for login Boolean existsByUsername(String username); 
	

}

