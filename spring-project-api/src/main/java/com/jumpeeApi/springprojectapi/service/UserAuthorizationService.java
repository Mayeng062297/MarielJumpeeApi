package com.jumpeeApi.springprojectapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpeeApi.springprojectapi.model.User;
import com.jumpeeApi.springprojectapi.repository.UserRepository;

// CLASS FOR LAST UPDATED DATE AND TIME OF USER AND ADMIN TO USERS ACCOUNT
@Service
public class UserAuthorizationService {

	@Autowired
	UserRepository repository;
	
	public boolean canUpdate(long loggedInUserId, long pathUserId) {
		if(loggedInUserId != pathUserId) {
		return false;
		
	}
		
	Optional <User> optionalUser = repository.findById(pathUserId);
	if(!optionalUser.isPresent())
		return false;
	
	User inDB = optionalUser.get();
	LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
	if(inDB.getLastUpdated() != null && inDB.getLastUpdated().isAfter(twentyFourHoursAgo)) {
		return false;
	}
	return true;
	}
	
}
