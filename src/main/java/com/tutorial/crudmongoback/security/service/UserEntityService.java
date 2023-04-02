package com.tutorial.crudmongoback.security.service;

import com.tutorial.crudmongoback.global.exceptions.AttributeException;
import com.tutorial.crudmongoback.security.dto.CreateUserDTO;
import com.tutorial.crudmongoback.security.dto.JwtTokenDTO;
import com.tutorial.crudmongoback.security.dto.LoginUserDTO;
import com.tutorial.crudmongoback.security.entity.UserEntity;
import com.tutorial.crudmongoback.security.enums.RolEnum;
import com.tutorial.crudmongoback.security.jwt.JwtProvider;
import com.tutorial.crudmongoback.security.repository.UserEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

	@Autowired
	UserEntityRepository userEntityRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
    public UserEntity create(CreateUserDTO dto) throws AttributeException {
        if(userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("username already in use");
        if(userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("email already in use");
        
        int	id = autoIncrement();
        String password = passwordEncoder.encode(dto.getPassword());
        
        List<RolEnum> roles = 
        		dto.getRoles().stream().map(rol -> RolEnum.valueOf(rol)).collect(Collectors.toList());
        
        UserEntity userEntity = new UserEntity(id, dto.getUsername(), dto.getEmail(), password, roles);
        
        return userEntityRepository.save(userEntity);
    }
    
    public JwtTokenDTO login(LoginUserDTO dto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDTO(token);
    }

    
    // private methods
    private int autoIncrement() {
    	List<UserEntity> users = userEntityRepository.findAll();
    	return users.isEmpty() ? 1 :
    		users.stream().max(Comparator.comparing(UserEntity::getId)).get().getId() + 1;
    }
	
}
