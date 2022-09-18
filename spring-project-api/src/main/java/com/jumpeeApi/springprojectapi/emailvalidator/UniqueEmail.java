package com.jumpeeApi.springprojectapi.emailvalidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;

//Class for Custom Validation for Unique email
@Target({ ElementType.FIELD})	//to be targeted on specific field only cannot be used anywhere
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)		//validated by another class UniqueEmailValidator
public @interface UniqueEmail {

	String message() default "This email is already exist";	//whenever email is exist, this message will show
	Class<?>[] groups() default { };
	Class<? extends Payload> [] payload() default{ };
}
