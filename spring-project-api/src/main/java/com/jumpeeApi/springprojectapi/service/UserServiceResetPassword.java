package com.jumpeeApi.springprojectapi.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jumpeeApi.springprojectapi.model.User;
import com.jumpeeApi.springprojectapi.repository.UserRepository;

@Service
public class UserServiceResetPassword {

		private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

		@Autowired
		UserRepository repository;
		
		PasswordEncoder passwordEncoder;		//encryption
		
		
		//PASSWORD ENCRYPTION
			public UserServiceResetPassword(UserRepository repository, PasswordEncoder passwordEncoder) {
				this.repository = repository;
				this.passwordEncoder = passwordEncoder;
			}
				
			//Validate user by email id.
		public String forgotPassword(String email) {

			Optional<User> userOptional = Optional
					.ofNullable(repository.findByEmail(email));

			if (!userOptional.isPresent()) {
				return "Invalid email id.";
			}

			User user = userOptional.get();
			user.setToken(generateToken());
			user.setTokenCreationDate(LocalDateTime.now());

			user = repository.save(user);

			return user.getToken();
		}
		//Validate the token
		public String resetPassword(String token, String password) {
	
			Optional<User> userOptional = Optional
					.ofNullable(repository.findByToken(token));

			if (!userOptional.isPresent()) {
				return "Invalid token.";
			}

			LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

			if (isTokenExpired(tokenCreationDate)) {
				return "Token expired.";

			}

			//Update the new password
			User user = userOptional.get();
			
			String encodedPassword = this.passwordEncoder.encode(user.getPassword());
			
			user.setPassword(encodedPassword);
			user.setToken(null);
			user.setTokenCreationDate(null);

			repository.save(user);

			return "Your password successfully updated.";
		}

		//Generate unique token
		/**
		 * Generate unique token. You may add multiple parameters to create a strong
		 * token.
		 * 
		 * @return unique token
		 */
		private String generateToken() {
			StringBuilder token = new StringBuilder();

			return token.append(UUID.randomUUID().toString())
					.append(UUID.randomUUID().toString()).toString();
		}

		/**
		 * Check whether the created token expired or not.
		 * 
		 * @param tokenCreationDate
		 * @return true or false
		 */
		private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

			LocalDateTime now = LocalDateTime.now();
			Duration diff = Duration.between(tokenCreationDate, now);

			return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
		}
	}

