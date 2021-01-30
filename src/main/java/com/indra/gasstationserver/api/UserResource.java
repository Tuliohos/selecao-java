package com.indra.gasstationserver.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indra.gasstationserver.foundation.excepction.LogicValidationException;
import com.indra.gasstationserver.model.user.User;
import com.indra.gasstationserver.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
	private IUserService service;

	@PostMapping
	public ResponseEntity create(@RequestBody User user){
		
		try {
			service.create(user);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (LogicValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity update(@RequestBody User user){
		
		try {
			service.update(user);
			return new ResponseEntity(HttpStatus.OK);
		} catch (LogicValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping
	public ResponseEntity delete(@RequestBody User user){
		
		try {
			service.delete(user);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value =  "/list")
	public ResponseEntity loadList() {
		try {
			List<User> userList = service.loadList();
			return ResponseEntity.ok(userList);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
