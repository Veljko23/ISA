package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DrivingStatisticDto;
import com.app.dto.PassengerDto;
import com.app.dto.RequestStatisticsDto;
import com.app.model.Passenger;
import com.app.model.User;
import com.app.service.PassengerService;

@RestController
@RequestMapping(value = "api/passengers")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	@GetMapping
	public ResponseEntity<List<PassengerDto>> getPassengers() {

		List<Passenger> passengers = passengerService.findAll();
		
		List<PassengerDto> passengerdtos = new ArrayList<PassengerDto>();
		
		for(Passenger passenger : passengers) {
			passengerdtos.add(new PassengerDto(passenger));
		}


		return new ResponseEntity<>(passengerdtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PassengerDto> getPassenger(@PathVariable Integer id) {
		try {
			Passenger user = passengerService.findOne(id);

			return new ResponseEntity<>(new PassengerDto(user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Passenger> savePassenger(@RequestBody Passenger user) {

		
		user = passengerService.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<?> updatePassenger(@RequestBody Passenger user, @PathVariable int id) {

		try {
			user.setId(user.getId());
			user.setName(user.getName());
			user.setSurname(user.getSurname());
			user.setAddress(user.getAddress());
			user.setEmail(user.getEmail());
			user.setPassword(user.getPassword());
			user.setBlock(user.isBlock());
			user.setNumber(user.getNumber());
			user.setPicture(user.getPicture());
			user.setRoles(user.getRoles());
			user.setDrivings(user.getDrivings());
			passengerService.save(user);
			return new ResponseEntity<Passenger>(user, HttpStatus.OK);
		} catch (NoSuchElementException  e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
//		// a course must exist
//		Passenger passengerFound = passengerService.findOne(user.getId());
//
//		if (passengerFound == null) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//
//		passengerFound.setName(passengerFound.getName());
//		passengerFound.setSurname(passengerFound.getSurname());
//		passengerFound.setAddress(passengerFound.getAddress());
//		passengerFound.setEmail(passengerFound.getEmail());
//		passengerFound.setPassword(passengerFound.getPassword());
//		passengerFound.setBlock(passengerFound.isBlock());
//		passengerFound.setNumber(passengerFound.getNumber());
//		passengerFound.setPicture(passengerFound.getPicture());
//		passengerFound.setDrivings(passengerFound.getDrivings());
//
//		passengerFound = passengerService.save(passengerFound);
//		return new ResponseEntity<>(passengerFound, HttpStatus.OK);
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
	
	@PostMapping(value = "/getStatistics")
	public ResponseEntity<ArrayList<DrivingStatisticDto>> getStatistics(@RequestBody RequestStatisticsDto dto) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<DrivingStatisticDto> dtos = new ArrayList<DrivingStatisticDto>();
		if(loggedUser.getRoles().get(0).getName().equals("ROLE_PASSENGER")) {
		dtos = passengerService
				.getDrivingStatisticsForPassengerAndDateRange(loggedUser.getId(), dto.getStart(), dto.getEnd());
		}
		if(loggedUser.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
			dtos = passengerService
					.getDrivingStatisticsForAllPassengerAndDateRange(dto.getStart(), dto.getEnd());
			}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getAllStatistics")
	public ResponseEntity<ArrayList<DrivingStatisticDto>> getAllStatistics(@RequestBody RequestStatisticsDto dto) {
		
		ArrayList<DrivingStatisticDto> dtos = passengerService.getDrivingStatisticsForAllPassengerAndDateRange(dto.getStart(), dto.getEnd());

		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}
}
