package com.tutorial.crudmongoback.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.crudmongoback.global.dto.MessageDto;
import com.tutorial.crudmongoback.global.exceptions.AttributeException;
import com.tutorial.crudmongoback.security.dto.CreateUserDTO;
import com.tutorial.crudmongoback.security.dto.JwtTokenDTO;
import com.tutorial.crudmongoback.security.dto.LoginUserDTO;
import com.tutorial.crudmongoback.security.entity.UserEntity;
import com.tutorial.crudmongoback.security.service.UserEntityService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserEntityService userEntityService;
	
	@PostMapping("/create")
	public ResponseEntity<MessageDto> create(@Valid @RequestBody CreateUserDTO dto) throws AttributeException{
		
		UserEntity userEntity = userEntityService.create(dto);
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "user " + userEntity.getUsername() + " have bean created"));

	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtTokenDTO> login(@Valid @RequestBody LoginUserDTO dto) throws AttributeException{
		
		JwtTokenDTO jwtTokenDTO = userEntityService.login(dto);
		return ResponseEntity.ok(jwtTokenDTO);

	}

}
