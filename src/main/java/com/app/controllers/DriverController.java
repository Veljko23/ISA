package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DriverDto;
import com.app.dto.DrivingStatisticDto;
import com.app.dto.RequestStatisticsDto;
import com.app.model.Driver;
import com.app.model.Passenger;
import com.app.model.User;
import com.app.service.DriverService;

@RestController
@RequestMapping(value = "api/drivers")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@GetMapping
	public ResponseEntity<List<DriverDto>> getDrivers() {

		List<Driver> drivers = driverService.findAll();
		
		List<DriverDto> driverdtos = new ArrayList<DriverDto>();
		
		for(Driver driver: drivers) {
			driverdtos.add(new DriverDto(driver));
		}

		return new ResponseEntity<>(driverdtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DriverDto> getDriver(@PathVariable Integer id) {
		try {
			Driver driver = driverService.findOne(id);

			return new ResponseEntity<>(new DriverDto(driver), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver) {

		
		driver = driverService.save(driver);
		return new ResponseEntity<>(driver, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver user, @PathVariable int id) {

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
			user.setVehicle(user.getVehicle());
			driverService.save(user);
			return new ResponseEntity<Driver>(user, HttpStatus.OK);
		} catch (NoSuchElementException  e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
	
//	@GetMapping(value = "/withDrivings/{id}")
//	public ResponseEntity<DriverWithDrivingsDto> getDriverWithDrivings(@PathVariable Integer id) {
//
//		Driver driver = driverService.getOneWithDrivings(id);
//
//		if (driver == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		DriverWithDrivingsDto driverDtoWithDrivings = new DriverWithDrivingsDto(driver);
//		return new ResponseEntity<>(driverDtoWithDrivings, HttpStatus.OK);
//	}
	
	@PostMapping(value = "/getStatistics")
	public ResponseEntity<ArrayList<DrivingStatisticDto>> getStatistics(@RequestBody RequestStatisticsDto dto) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ArrayList<DrivingStatisticDto> dtos = driverService.getDrivingStatisticsForDriverAndDateRange(loggedUser.getId(), dto.getStart(), dto.getEnd());

		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}
	@PreAuthorize("hasRole('ROLE_DRIVER')")
	@PostMapping(value = "/getAllStatistics")
	public ResponseEntity<ArrayList<DrivingStatisticDto>> getAllStatistics(@RequestBody RequestStatisticsDto dto) {
		
		ArrayList<DrivingStatisticDto> dtos = driverService.getDrivingStatisticsForAllDriverAndDateRange(dto.getStart(), dto.getEnd());

		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}
}
