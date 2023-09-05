package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.VehicleDTO;
import com.app.model.Vehicle;
import com.app.service.VehicleService;

@RestController
@RequestMapping(value = "api/vehicles")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;

	@GetMapping
	public ResponseEntity<List<VehicleDTO>> getVehicles() {

		List<Vehicle> vehicles = vehicleService.findAll();

		List<VehicleDTO> vehicleDtos = new ArrayList<VehicleDTO>();
		for (Vehicle vehicle : vehicles) {
			vehicleDtos.add(new VehicleDTO(vehicle));
		}

		return new ResponseEntity<>(vehicleDtos, HttpStatus.OK);
	}
}
