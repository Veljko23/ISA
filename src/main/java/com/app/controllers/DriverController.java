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
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Driver;
import com.app.service.DriverService;

@RestController
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@GetMapping
	public ResponseEntity<List<Driver>> getDrivers() {

		List<Driver> drivers = driverService.findAll();


		return new ResponseEntity<>(drivers, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Driver> getDriver(@PathVariable Integer id) {

		Driver driver = driverService.findOne(id);

		if (driver == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(driver, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver) {

		
		driver = driverService.save(driver);
		return new ResponseEntity<>(driver, HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) {

		// a course must exist
		Driver driverFound = driverService.findOne(driver.getId());

		if (driverFound == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		driverFound.setName(driverFound.getName());
		driverFound.setSurname(driverFound.getSurname());
		driverFound.setAddress(driverFound.getAddress());
		driverFound.setEmail(driverFound.getEmail());
		driverFound.setPassword(driverFound.getPassword());
		driverFound.setBlock(driverFound.isBlock());
		driverFound.setNumber(driverFound.getNumber());
		driverFound.setPicture(driverFound.getPicture());
		driverFound.setDocuments(driverFound.getDocuments());
		driverFound.setActive(driverFound.isActive());
		driverFound.setDrivings(driverFound.getDrivings());
		driverFound.setVehicle(driverFound.getVehicle());

		driverFound = driverService.save(driverFound);
		return new ResponseEntity<>(driverFound, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {

		Driver driver = driverService.findOne(id);

		if (driver != null) {
			driverService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
