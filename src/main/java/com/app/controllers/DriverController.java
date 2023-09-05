package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.app.model.User;
import com.app.service.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	/////////////////////////////////////////////////////
	
	@PostMapping(value = "/createDriver", consumes = "application/json")
    public String createDriver(@RequestBody DriverDto driverDto){
		
       
		Driver driver = driverService.createDriver(driverDto);
	       String message  = null;
	       if(driver == null) {
	           message = "unsuccesfull";
	       }else {
	           message = "succesfull";
	       }
	        return getJsonResponse("message", message);
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
	
	
	////////////////////////////////////////////////////

	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<DriverDto> updateDriver(@RequestBody DriverDto driverDto, @PathVariable Integer id) {

		Driver updatedDriver = driverService.updateDriver(driverDto, id);

		return new ResponseEntity<>(new DriverDto(updatedDriver), HttpStatus.OK);

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
	
	
	@PostMapping(value = "/getStatistics")
	public ResponseEntity<ArrayList<DrivingStatisticDto>> getStatistics(@RequestBody RequestStatisticsDto dto) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<DrivingStatisticDto> dtos = new ArrayList<DrivingStatisticDto>();
		if(loggedUser.getRoles().get(0).getName().equals("ROLE_DRIVER")) {
			dtos = driverService.getDrivingStatisticsForDriverAndDateRange(loggedUser.getId(), dto.getStart(), dto.getEnd());
		}
		if(loggedUser.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
			dtos = driverService.getDrivingStatisticsForAllDriverAndDateRange(dto.getStart(), dto.getEnd());
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}
	@PreAuthorize("hasRole('ROLE_DRIVER')")
	@PostMapping(value = "/getAllStatistics")
	public ResponseEntity<ArrayList<DrivingStatisticDto>> getAllStatistics(@RequestBody RequestStatisticsDto dto) {
		
		ArrayList<DrivingStatisticDto> dtos = driverService.getDrivingStatisticsForAllDriverAndDateRange(dto.getStart(), dto.getEnd());

		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}
	
	@GetMapping(value = "/logout")
	public ResponseEntity<Void> driverInactive(){
		
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(loggedUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(loggedUser instanceof Driver) {
			((Driver) loggedUser).setActive(false);
			driverService.save((Driver) loggedUser);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	//////////////////////////////////////////////////////////
	@GetMapping(value = "/status")
	public ResponseEntity<String> driverStatus(){
		
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(loggedUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(loggedUser instanceof Driver) {
			((Driver) loggedUser).setActive(!((Driver) loggedUser).isActive());
			driverService.save((Driver) loggedUser);
			
			 String status = ((Driver) loggedUser).isActive() ? "active" : "inactive";

		     return ResponseEntity.ok("{\"status\":\"" + status + "\"}");
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
