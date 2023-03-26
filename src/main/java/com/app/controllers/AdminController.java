package com.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Admin;
import com.app.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Admin> getAdmin(@PathVariable Integer id) {

		Admin admin = adminService.findOne(id);

		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {

		
		admin = adminService.save(admin);
		return new ResponseEntity<>(admin, HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {

		// a course must exist
		Admin adminFound = adminService.findOne(admin.getId());

		if (adminFound == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		adminFound.setName(adminFound.getName());
		adminFound.setSurname(adminFound.getSurname());
		adminFound.setUsername(adminFound.getUsername());
		adminFound.setPassword(adminFound.getPassword());
		adminFound.setImage(adminFound.getImage());

		adminFound = adminService.save(adminFound);
		return new ResponseEntity<>(adminFound, HttpStatus.OK);
	}

}
