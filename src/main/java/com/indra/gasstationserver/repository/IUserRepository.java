package com.indra.gasstationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.indra.gasstationserver.model.user.User;

@Component
public interface IUserRepository extends JpaRepository<User, Long>{
	
	public Boolean existsByEmail(String userEmail);

}
