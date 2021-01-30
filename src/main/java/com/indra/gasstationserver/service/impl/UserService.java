package com.indra.gasstationserver.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.indra.gasstationserver.foundation.excepction.LogicValidationException;
import com.indra.gasstationserver.model.user.User;
import com.indra.gasstationserver.repository.IUserRepository;
import com.indra.gasstationserver.service.IUserService;

@Component
public class UserService implements IUserService {

	@Autowired
	private IUserRepository repository;
	
	@Override
	public void create(User user) {
		Boolean isCreating = Boolean.TRUE;
		this.validate(user, isCreating);
		this.fillDates(user, isCreating);
		repository.save(user);
	}
	
	@Override
	public void update(User user) {
		this.validate(user, Boolean.FALSE);
		
		Optional<User> optionalUser = repository.findById(user.getId());
		if(optionalUser.isPresent()) {
			User oldUser = optionalUser.get();
			
				oldUser.setName(user.getName());
				oldUser.setEmail(user.getEmail());
				oldUser.setBirthday(user.getBirthday());
				
			this.fillDates(oldUser, Boolean.FALSE);
			repository.save(oldUser);
			
		}else{
			throw new LogicValidationException("Fuel History not found!");
		}
	}
	
	@Override
	public void delete(User user) {
		repository.delete(user);
	}
	
	@Override
	public List<User> loadList(){
		return repository.findAll(Sort.by("name"));
	}
	
	private void validate(User user, Boolean isCreating) {
		
		if(Objects.isNull(user)) {
			throw new LogicValidationException("Error creating user.");
		}
		
		if(ObjectUtils.isEmpty(user.getName())) {
			throw new LogicValidationException("Name is required!");
		}
		
		if(ObjectUtils.isEmpty(user.getEmail())) {
			throw new LogicValidationException("E-mail is required!");
		}
		
		if(isCreating && repository.existsByEmail(user.getEmail())){
			throw new LogicValidationException("E-mail already registered in the system");
		}
		
	}
	
	private void fillDates(User user, Boolean isCreating) {
		if(isCreating) {
			user.setCreationDateTime(new Date());
		}
		user.setModificationDateTime(new Date());
	}
}
