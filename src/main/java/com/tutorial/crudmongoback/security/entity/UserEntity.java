package com.tutorial.crudmongoback.security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tutorial.crudmongoback.security.enums.RolEnum;

@Entity
@Table (name = "users")
public class UserEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
    private String username;
	@Column
    private String email;
	@Column
    private String password;
    
    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<RolEnum> roles;
    
	public UserEntity() {
	}

	public UserEntity(int id, String username, String email, String password, List<RolEnum> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RolEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RolEnum> roles) {
        this.roles = roles;
    }
}
