package com.jumpeeApi.springprojectapi.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonView;
import com.jumpeeApi.springprojectapi.emailvalidator.UniqueEmail;
import com.jumpeeApi.springprojectapi.jsonview.View;


@Entity
@Table(name = "users")
public class User {
	
	
		@Id		//primary key
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@JsonView(View.Base.class)
		@Column(name="user_id")
		private long id;
		

		@NotBlank (message="Full name cannot be empty")
		@NotNull(message="Full name cannot be null!")
		@JsonView(View.Base.class)		//this field will only be visible on postman 
		private String fullname;
		
		//@Size(max = 13, message = "invalid mobile number entered ")
		@Pattern(regexp ="^(09|\\+639)\\d{9}$", message ="invalid contact number entered ")
		@NotNull(message="Phone number cannot be null!")
		@JsonView(View.Base.class)
		private String phone_number;
		
		private String address;
		
		@UniqueEmail
		@NotNull(message="Email cannot be null!")
		@Email(message="Please enter a valid email address!")	//Email validation format		
		@JsonView(View.Base.class)
		private String email;
		
		@NotNull(message="Username cannot be null!")
		@JsonView(View.Base.class)
		private String username;
		
	//	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,16}$", message = "The password can contain alphanumeric characters only")
	//	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "The password can contain alphanumeric characters only") //d pa nagana @Pattern
	//	@Pattern(regexp =".[A-Z]..[0-9].*", message = "The password can contain alphanumeric characters only")
	//	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Must contain at least one (number, lowercase, uppercase, special character)." + " Must contain a length of at least 8 characters and a maximum of 20 characters.")
		@NotNull(message="Password cannot be null!")
		@Size(min = 8, message = "Password must be minimum of 8 characters")
		private String password;
		
		private String token;	//reset password token
		@Column(columnDefinition = "TIMESTAMP")	//reset password token
		private LocalDateTime tokenCreationDate;
		
		@JsonView(View.Base.class)
		private String role;
		
		private LocalDateTime lastUpdated;		//last updated of user

				
		//getters and setters
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}

		public String getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}

		public LocalDateTime getTokenCreationDate() {
			return tokenCreationDate;
		}
		public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
			this.tokenCreationDate = tokenCreationDate;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public LocalDateTime getLastUpdated() {
			return lastUpdated;
		}
		public void setLastUpdated(LocalDateTime lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
				
}
