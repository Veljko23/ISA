package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.dto.UserRequest;
import com.app.model.User;
import com.app.security.auth.ChangePasswordRequest;
import com.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {

		List<User> users = userService.findByRole();
		
		List<UserDto> userdtos = new ArrayList<UserDto>();
		
		for(User user: users) {
			userdtos.add(new UserDto(user));
		}

		return new ResponseEntity<>(userdtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
	    User user = userService.findOne(id);

	    if (user == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    UserDto userDto = new UserDto(user);
	    return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
	    User newUser = new User();
	    newUser.setName(userDto.getName());
	    newUser.setSurname(userDto.getSurname());
	    newUser.setAddress(userDto.getAddress());
	    newUser.setEmail(userDto.getEmail());
	    newUser.setBlock(userDto.isBlock());
	    newUser.setNumber(userDto.getNumber());
	    newUser.setPicture(userDto.getPicture());

	    User savedUser = userService.save(newUser);

	    return new ResponseEntity<>(new UserDto(savedUser), HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest, @PathVariable Integer id) {
	    
		User updatedUser = userService.update(userRequest, id);

	    return new ResponseEntity<>(new UserRequest(updatedUser), HttpStatus.OK);
	}
	
	////////////////////////////////za aktivaciju!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(value = "/activate/{id}", consumes = "application/json")
	public ResponseEntity<UserRequest> activateUser(@PathVariable Integer id) {
		
		User activatedUser = userService.activate(id);

	    return new ResponseEntity<>(new UserRequest(activatedUser), HttpStatus.OK);
	}
	
	@PostMapping(value = "/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam String email) {
	    userService.forgotPassword(email);
		return ResponseEntity.ok("Password reset successful."); 
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
	
	@PostMapping(value = "/register", consumes = "application/json")
    public String registerUser(@RequestBody UserRequest userRequest) {
		User user = userService.create(userRequest);
		String message  = null;
	       if(user == null) {
	           message = "unsuccessfull";
	       }else {
	           message = "successfull";
	       }
	        return getJsonResponse("message", message);
    }
	
	@GetMapping(value = "/getLoggedUser")
    public String getLoggedUser() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                
        if(loggedUser == null) {
            return getJsonResponse("message", "not logged");
        }
        
        String role = loggedUser.getRoles().get(0).getName();
        int id = loggedUser.getId();
        boolean block = loggedUser.isBlock();
        return getJsonResponseForId("message", role, "id", id, "block", block);
    }
	
	private String getJsonResponse(String key, String value) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(Map.of(key, value));
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
	
	private String getJsonResponseForId(String key, String value, String key1, int value1, String key2, boolean value2) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(Map.of(key, value, key1, value1, key2, value2));
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
	
	@PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
        @RequestBody ChangePasswordRequest changePasswordRequest,
        Authentication authentication) {

        User loggedUser = (User) authentication.getPrincipal();

        if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), loggedUser.getPassword())) {
            loggedUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userService.save(loggedUser);
            return ResponseEntity.ok("Password changed successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect old password");
        }
    }
	
	
}
