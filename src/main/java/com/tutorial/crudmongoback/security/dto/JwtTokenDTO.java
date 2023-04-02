package com.tutorial.crudmongoback.security.dto;

public class JwtTokenDTO {
	
	private String token;

	public JwtTokenDTO() {
		super();
	}

	public JwtTokenDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
