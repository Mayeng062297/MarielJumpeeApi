package com.jumpeeApi.springprojectapi.emailvalidator;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jumpeeApi.springprojectapi.model.User;
import com.jumpeeApi.springprojectapi.repository.UserRepository;


//Class for Custom Validation for Unique email
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {		//constraint validation name, and field type of email
	
@Autowired
UserRepository repository;		//need this for the query

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User inDb = repository.findByEmail(value);		
		if(inDb == null) {								
			return true;								
		}
		return false;
	}
	//Validation The User in db by calling the userRepository.findByEmail with this value
	//if in db is null, which means we dont have any user in database having this email
	//then we can return true otherwise false.
}
