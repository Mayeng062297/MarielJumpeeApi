package com.jumpeeApi.springprojectapi.emailvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jumpeeApi.springprojectapi.model.User;
import com.jumpeeApi.springprojectapi.repository.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	
@Autowired
UserRepository repository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User inDb = repository.findByEmail(value);
		if(inDb == null) {
			return true;
		}
		return false;
	}

	
}
