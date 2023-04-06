package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Location;
import com.app.service.LocationService;

@RestController
@RequestMapping(value = "api/locations")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping
	public ResponseEntity<List<Location>> getLocations() {

		List<Location> locations = locationService.findAll();


		return new ResponseEntity<>(locations, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Location> getLocation(@PathVariable Integer id) {

		Location lokacija = locationService.findOne(id);

		if (lokacija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(lokacija, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Location> saveLocation(@RequestBody Location location) {

		
		location = locationService.save(location);
		return new ResponseEntity<>(location, HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<Location> updateLocation(@RequestBody Location location) {

		// a course must exist
		Location locationFound = locationService.findOne(location.getId());

		if (locationFound == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		locationFound.setLongitude(locationFound.getLongitude());
		locationFound.setLatitude(locationFound.getLatitude());

		locationFound = locationService.save(locationFound);
		return new ResponseEntity<>(locationFound, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {

		Location location = locationService.findOne(id);

		if (location != null) {
			locationService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
