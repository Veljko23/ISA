package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.app.dto.DrivingDto;
import com.app.dto.DrivingSortDto;
import com.app.dto.DrivingWithPathsAndPassengersDto;
import com.app.dto.DrivingWithPathsDto;
import com.app.model.Driving;
import com.app.model.User;
import com.app.service.DrivingService;

@RestController
@RequestMapping(value = "api/drivings")
public class DrivingController {

	@Autowired
	private DrivingService drivingService;
	
	@GetMapping
	public ResponseEntity<List<DrivingDto>> getDrivings() {

		List<Driving> drivings = drivingService.findAll();
		
		List<DrivingDto> drivingdtos = new ArrayList<DrivingDto>();
		for(Driving driving : drivings) {
			drivingdtos.add(new DrivingDto(driving));
		}


		return new ResponseEntity<>(drivingdtos, HttpStatus.OK); 
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Driving> getDriving(@PathVariable Integer id) {

		Driving driving = drivingService.findOne(id);

		if (driving == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(driving, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Driving> saveDriving(@RequestBody Driving driving) {

		
		driving = drivingService.save(driving);
		return new ResponseEntity<>(driving, HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<Driving> updateDriving(@RequestBody Driving driving) {

		// a course must exist
		Driving drivingFound = drivingService.findOne(driving.getId());

		if (drivingFound == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		drivingFound.setDriver(drivingFound.getDriver());
		drivingFound.setDrivingStatus(drivingFound.getDrivingStatus());
		drivingFound.setStart(drivingFound.getStart());
		drivingFound.setEnd(drivingFound.getEnd());
		drivingFound.setEstimatedTime(drivingFound.getEstimatedTime());
		drivingFound.setPassengers(drivingFound.getPassengers());
		drivingFound.setPaths(drivingFound.getPaths());
		drivingFound.setPayments(drivingFound.getPayments());
		drivingFound.setPrice(drivingFound.getPrice());
		drivingFound.setVehicleType(drivingFound.getVehicleType());

		drivingFound = drivingService.save(drivingFound);
		return new ResponseEntity<>(drivingFound, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDriving(@PathVariable Integer id) {

		Driving driving = drivingService.findOne(id);

		if (driving != null) {
			drivingService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/passengersDrivingHistory")
	public ResponseEntity<List<DrivingDto>> getPassengersDrivingHistory(@RequestBody DrivingSortDto drivingSortDto) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
		List<Driving> drivings = drivingService.getPassengersDrivingHistory(loggedUser, drivingSortDto);
		if(drivings == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
		}
		
		List<DrivingDto> drivingdtos = new ArrayList<DrivingDto>();
		for(Driving driving : drivings) {
			drivingdtos.add(new DrivingDto(driving));
		}


		return new ResponseEntity<>(drivingdtos, HttpStatus.OK); 
		

	}
	
	@GetMapping(value = "/driversDrivingHistory")
	public ResponseEntity<List<DrivingDto>> getDriversDrivingHistory(@RequestBody DrivingSortDto drivingSortDto) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
		List<Driving> drivings = drivingService.getDriversDrivingHistory(loggedUser, drivingSortDto);
		if(drivings == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
		}
		
		List<DrivingDto> drivingdtos = new ArrayList<DrivingDto>();
		for(Driving driving : drivings) {
			drivingdtos.add(new DrivingDto(driving));
		}


		return new ResponseEntity<>(drivingdtos, HttpStatus.OK); 
		
	}
	
	@GetMapping(value = "/withPaths/{id}")
    public ResponseEntity<DrivingWithPathsDto> getOneWithDriverAndPaths(@PathVariable Integer id) {

        Driving driving = drivingService.getOneDrivingWithPaths(id);

        if (driving == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DrivingWithPathsDto andPathsDto = new DrivingWithPathsDto(driving);
        return new ResponseEntity<>(andPathsDto, HttpStatus.OK);
    }
	
	@GetMapping(value = "/withPathsAndPassengers/{id}")
	public ResponseEntity<DrivingWithPathsAndPassengersDto> getOneWithPathsAndPassengers(@PathVariable Integer id) {

		Driving driving = drivingService.getOneDrivingWithPathsAndPassengers(id);

		if (driving == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		DrivingWithPathsAndPassengersDto pathsAndPassengersDto = new DrivingWithPathsAndPassengersDto(driving);
		return new ResponseEntity<>(pathsAndPassengersDto, HttpStatus.OK);
	}
}
