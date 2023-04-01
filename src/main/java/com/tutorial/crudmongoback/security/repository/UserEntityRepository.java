package com.tutorial.crudmongoback.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.crudmongoback.security.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);

	Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
