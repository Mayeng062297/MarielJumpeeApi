package com.jumpeeApi.springprojectapi.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.filter.TokenFilter;

//this class is to override the configuration of spring security http
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 		//has role based admin
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserAuthService userAuthService;
	
	
	@Override //override http security
	protected void configure(HttpSecurity http) throws Exception { 		 //configure the http method
		http.csrf().disable();  //disabled the security for post sending request when creating a user
		http.httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint()	{

			@Override	//Authentication entry point
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				response.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());
				
			}	
		});
				
		http
		.authorizeRequests()
		
		//REGISTERED USER
		.antMatchers("/display-users").hasAnyRole("admin") 
		.antMatchers("/display-users/{id}").hasAnyRole("admin") 
		
		//LOGIN
		.antMatchers("/login").authenticated()					
		
		//MY ACCOUNT PAGE
		.antMatchers("/account-details").authenticated()		
		.antMatchers(HttpMethod.DELETE, "/delete-user/{id}").authenticated() 	
		.antMatchers(HttpMethod.PUT, "/edit-user-details/{id}").authenticated() 	
		
		//OK ADMIN ACCESS FOR PRODUCT PAGE 
		.antMatchers("/admin-access").hasAnyRole("admin") 
		.antMatchers("/add-product").hasAnyRole("admin") 
		.antMatchers("/update-product/{id}").hasAnyRole("admin") 
		.antMatchers("/delete-product/{id}").hasAnyRole("admin") 
			
		.and()
		.authorizeRequests().anyRequest().permitAll();		//any specified endpoint authorized request to be permitted

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	//configure http with session management session creation policy with status

	}
 
	//setting and passing password encoder
	//override another configuration method,going to run and it has a userDetailsService,we will pass the user details to this one
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {		//returning password encoder and this is the method name
		return new BCryptPasswordEncoder();	
	}
	
}


//.antMatchers("/secured-admin").hasAnyRole("admin") 	// ok testing endpoint for admin authentication
//.antMatchers(HttpMethod.PUT, "/edit-address/{id}").authenticated() //authentication for putmapping edit address
