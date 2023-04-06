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

import com.app.model.Passenger;
import com.app.service.PassengerService;

@RestController
@RequestMapping(value = "api/passengers")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	@GetMapping
	public ResponseEntity<List<Passenger>> getPassengers() {

		List<Passenger> passengers = passengerService.findAll();


		return new ResponseEntity<>(passengers, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Passenger> getPassenger(@PathVariable Integer id) {

		Passenger user = passengerService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Passenger> savePassenger(@RequestBody Passenger user) {

		
		user = passengerService.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger user) {

		// a course must exist
		Passenger passengerFound = passengerService.findOne(user.getId());

		if (passengerFound == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		passengerFound.setName(passengerFound.getName());
		passengerFound.setSurname(passengerFound.getSurname());
		passengerFound.setAddress(passengerFound.getAddress());
		passengerFound.setEmail(passengerFound.getEmail());
		passengerFound.setPassword(passengerFound.getPassword());
		passengerFound.setBlock(passengerFound.isBlock());
		passengerFound.setNumber(passengerFound.getNumber());
		passengerFound.setPicture(passengerFound.getPicture());
		passengerFound.setDrivings(passengerFound.getDrivings());

		passengerFound = passengerService.save(passengerFound);
		return new ResponseEntity<>(passengerFound, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePassenger(@PathVariable Integer id) {

		Passenger user = passengerService.findOne(id);

		if (user != null) {
			passengerService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
