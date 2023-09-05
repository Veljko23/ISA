package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DriversProfileChangeDto;
import com.app.enums.DriversProfileChangeStatus;
import com.app.model.DriversProfileChange;
import com.app.service.DriversProfileChangeService;

@RestController
@RequestMapping(value = "api/driversChange")
public class DriversProfileChangeController {

	@Autowired
    private DriversProfileChangeService profileChangeService;
	
	@GetMapping
	public ResponseEntity<List<DriversProfileChangeDto>> getChanges() {

		List<DriversProfileChange> requests = profileChangeService.findAll();

		List<DriversProfileChangeDto> requestDtos = new ArrayList<DriversProfileChangeDto>();
		for (DriversProfileChange request : requests) {
			if(request.getStatus() == DriversProfileChangeStatus.OnWait)
				requestDtos.add(new DriversProfileChangeDto(request));
		}

		return new ResponseEntity<>(requestDtos, HttpStatus.OK);
	}
	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<DriversProfileChangeDto> getDriver(@PathVariable Integer id) {
//		try {
//			Driver driver = driverService.findOne(id);
//
//			return new ResponseEntity<>(new DriversProfileChangeDto(driver), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//	}

    @PostMapping
    public ResponseEntity<String> requestProfileChange(@RequestBody DriversProfileChangeDto dto) {
        profileChangeService.requestProfileChange(dto);
        return ResponseEntity.ok("Profile change request submitted");
    }
    
	@CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/accept/{id}")
    public ResponseEntity<String> acceptRequestProfileChange(@PathVariable Integer id) {
        profileChangeService.acceptRequestProfileChange(id);
        return ResponseEntity.ok("Profile change request submitted");
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/deny/{id}")
    public ResponseEntity<String> denyRequestProfileChange(@PathVariable Integer id) {
        profileChangeService.denyRequestProfileChange(id);
        return ResponseEntity.ok("Profile change request submitted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriversProfileChange> getProfileChange(@PathVariable int id) {
        DriversProfileChange profileChange = profileChangeService.getProfileChangeById(id);
        return ResponseEntity.ok(profileChange);
    }
}
