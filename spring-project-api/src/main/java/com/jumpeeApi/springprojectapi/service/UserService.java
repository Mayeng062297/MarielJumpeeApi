package com.jumpeeApi.springprojectapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jumpeeApi.springprojectapi.error.NotFoundException;
import com.jumpeeApi.springprojectapi.model.User;
import com.jumpeeApi.springprojectapi.repository.UserRepository;

//contains business logic. It defines functionalities you provide, how they are accessed, and what to pass and get in return
@Service
public class UserService {
	
	@Autowired
	UserRepository repository;		//repository class
	
	PasswordEncoder passwordEncoder;	
	
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {	//PASSWORD ENCRYPTION
		this.repository = repository;
		this.passwordEncoder = passwordEncoder; 	
		}
			
	public List<User> getUsers() {		//GET 
		return repository.findAll();
	}
	
	public User getUserById(long id) {	//GET BY ID
		return this.repository.findById(id).orElseThrow(() -> new NotFoundException("User does not exist"));
	}
	
	public User save(User users) {	//POST REGISTER
		String encodedPassword = this.passwordEncoder.encode(users.getPassword());
		users.setPassword(encodedPassword);
		return this.repository.save(users);
	}
	
	//PUT USER DETAILS 
	public User updateUserDetail(Long id, User user) 
	{
	User inDB = repository.getOne(id);
	inDB.setFullname(user.getFullname());
	inDB.setPhone_number(user.getPhone_number());
	inDB.setUsername(user.getUsername());
//	inDB.setLastUpdated(LocalDateTime.now()); //storing last updated time of the user
	return this.repository.save(inDB);
	}


	//PUT USER ADDRESS 
	public User updateUserAddress(Long id, User user) {
	User inDB = repository.getOne(id);
	inDB.setAddress(user.getAddress());
	inDB.setLastUpdated(LocalDateTime.now()); //storing last updated time of the user
		return this.repository.save(inDB);
	}

	
	//DELETE 
	public void deleteUser(long id) {
		repository.deleteById(id);
		
	}

}
