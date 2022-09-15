package com.jumpeeApi.springprojectapi;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpeeApi.springprojectapi.PRODUCT.Product;
import com.jumpeeApi.springprojectapi.PRODUCT.ProductRepository;
import com.jumpeeApi.springprojectapi.configuration.AppUser;
import com.jumpeeApi.springprojectapi.configuration.LoggedInUser;
import com.jumpeeApi.springprojectapi.jsonview.View;


// @SpringBootApplication(exclude = SecurityAutoConfiguration.class)	//excluding spring security in this app
@SpringBootApplication			//
public class SpringProjectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApiApplication.class, args);		
	}

	
	@Bean
	CommandLineRunner run(ProductRepository productRepository) {
	return new CommandLineRunner() {

		@Override
		public void run(String... args) throws Exception {
		IntStream.rangeClosed(1,50).forEach(i -> {
		Product product = new Product();
		product.setProductName("Product" + i);
		product.setStock("Stock" + i);
		product.setPrice(i);
		productRepository.save(product);
		});
	}
	};
}


}









//@RestController

	/*	ok Testing for authorized users and admin registered on app 
	//FOR SPRING SECURITY AS Registered user and admin ACCESS
	@GetMapping("/secured")
	@JsonView(View.Base.class)
	public Object secured(@LoggedInUser AppUser appUser) {
		return appUser.getUser();	//returning the details of the user
	}
	*/
	
	/* OK for Testing Admin Authorization 
	//FOR SPRING SECURITY AS ADMIN ACCESS
	@GetMapping("/secured-admin")
	@PreAuthorize("hasRole('ROLE_admin')")
	public String securedAdmin() {
		return "Only Admin can see this";
	}
	*/
	
	/*
	//endpoint /public, has built in login page when you send a request
	@GetMapping("/public")
	public String pub() {
		return "This is public";
	}
	*/

