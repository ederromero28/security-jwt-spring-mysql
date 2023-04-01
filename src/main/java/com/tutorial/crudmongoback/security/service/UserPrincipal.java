package com.tutorial.crudmongoback.security.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tutorial.crudmongoback.security.entity.UserEntity;

public class UserPrincipal implements UserDetails{
	
	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	public UserPrincipal(String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserPrincipal build(UserEntity userEntity) {
		Collection<GrantedAuthority> authorities = userEntity.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());
		return new UserPrincipal(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getEmail() {
		return email;
	}
}
