package com.jumpeeApi.springprojectapi.controller;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonView;
import com.jumpeeApi.springprojectapi.configuration.AppUser;
import com.jumpeeApi.springprojectapi.configuration.LoggedInUser;
import com.jumpeeApi.springprojectapi.jsonview.View;
import com.jumpeeApi.springprojectapi.model.User;
import com.jumpeeApi.springprojectapi.service.UserService;


@RestController		//handle rest api request
public class UserRestController {

	@Autowired		//it allows spring to resolve and inject collaborating beans into our bean
	UserService userService;	//service class
	
	
	@GetMapping("/display-users")	//GET METHOD DISPLAY ALL USERS
	@PreAuthorize("hasRole('ROLE_admin')")
	@JsonView(View.Base.class)  //fields with this annotation will only be visible in response view
	List<User> getUsers() {
		return userService.getUsers();
	}
		
	@GetMapping("/display-users/{id}")	//GET METHOD DISPLAY USERS BY ID
	@PreAuthorize("hasRole('ROLE_admin')")
	@JsonView(View.Base.class) //fields with this annotation will only be visible in response view
	public User getUser(@PathVariable long id) {
		return this.userService.getUserById(id);
	}

		
	@PostMapping("/register")	//POST METHOD USER REGISTRATION
	public String createUser(@Valid @RequestBody User users)
	{
		userService.save(users);		
		return "Hi, You are Sucessfully Registered!";
	}
	
	
	@GetMapping("/login")	//GET METHOD Login As Authorized User and Admin 
	@JsonView(View.Base.class)
	public Object secured(@LoggedInUser AppUser appUser) {
		return appUser.getUser();	//returning the details of the user
	}
	
	@GetMapping("/account-details")	//GET METHOD Login As Authorized User and Admin 
	@JsonView(View.Base.class)
	public Object account(@LoggedInUser AppUser appUser) {
		return appUser.getUser();	//returning the details of the user
	}
	
	@DeleteMapping("/delete-user/{id}")	//DELETE METHOD Account deletion by registered user and as admins, cannot delete by another user
	@PreAuthorize("@userAuthorizationService.canUpdate(principal.user.id, #id) OR hasRole('ROLE_admin')") //can access by authorize user only
	public String deleteUser(@PathVariable long id) {
	userService.deleteUser(id);
	return "Delete user with the id:" +id;
	}
	
	
	@PutMapping("/edit-user-details/{id}")	//PUT METHOD Modifying User Account Details by registered user and admins
	@PreAuthorize("@userAuthorizationService.canUpdate(principal.user.id, #id) OR hasRole('ROLE_admin')")	//can update by the user and admins only 
	public String updateUserDetail(@RequestBody User user, @PathVariable long id) 
	{	
	userService.updateUserDetail(id, user);
	return "Account Details is Successfully Updated!";
	}
	
	
	@PutMapping("/edit-address/{id}")	//PUT METHOD for updating specific field like customer address only
	@PreAuthorize("@userAuthorizationService.canUpdate(principal.user.id, #id) OR hasRole('ROLE_admin')")	//can update by the user and admins only 
	public User updateUserAddress(@RequestBody User user, @PathVariable long id) 
	{	
	return this.userService.updateUserAddress(id, user); 	//response complete user details
	}
}



/*
//POST METHOD returning all users details GUMAGANA TO FOR REGISTRATION
@PostMapping("/register")
@JsonView(View.Base.class) //fields with this code will only be visible on postman view
public User createUser(@Valid @RequestBody User users)
{
	return this.userService.save(users);			
}
*/	