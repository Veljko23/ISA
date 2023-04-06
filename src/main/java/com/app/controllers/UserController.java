package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserRequest;
import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {

		List<User> users = userService.findAll();


		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {

		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<User> saveUser(@RequestBody UserRequest userRequest) {

		
		User user = userService.create(userRequest);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		// a course must exist
		User userFound = userService.findOne(user.getId());

		if (userFound == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		userFound.setName(userFound.getName());
		userFound.setSurname(userFound.getSurname());
		userFound.setAddress(userFound.getAddress());
		userFound.setEmail(userFound.getEmail());
		userFound.setPassword(userFound.getPassword());
		userFound.setBlock(userFound.isBlock());
		userFound.setNumber(userFound.getNumber());
		userFound.setPicture(userFound.getPicture());

		userFound = userService.save(userFound);
		return new ResponseEntity<>(userFound, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

		User user = userService.findOne(id);

		if (user != null) {
			userService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
