package com.jumpeeApi.springprojectapi.configuration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.UserDetails;

import com.jumpeeApi.springprojectapi.model.User;

import lombok.Data;

@Data
public class AppUser implements UserDetails {


	private static final long serialVersionUID = 1L;
	User users;
	
	public AppUser(User users) {
		this.users = users;
	}

	@Override		//defining if what role will be authorized
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_" + this.users.getRole());
	}
	

	@Override
	public String getUsername() {
		return this.users.getUsername();
	}

	@Override
	public String getPassword() {
		return this.users.getPassword();
	}
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Object getUser() {
		// TODO Auto-generated method stub
		return users;
	}


}
