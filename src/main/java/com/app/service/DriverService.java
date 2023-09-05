package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.DriverDto;
import com.app.dto.DrivingStatisticDto;
import com.app.model.Driver;
import com.app.model.Driving;
import com.app.model.Path;
import com.app.model.Role;
import com.app.model.Vehicle;
import com.app.repository.DriverRepository;
import com.app.repository.VehicleRepository;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Driver findOne(Integer id) {
		return driverRepository.findById(id).get();
	}
	
	public List<Driver> findAll(){
		return driverRepository.findAll();
	}
	
	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}
	
	public Driver createDriver(DriverDto driverDto) {
		Driver driver = driverDto.toDriver();
		if (driver == null || driver.getEmail() == null || driver.getPassword() == null) {
			return null;
		}

		if (isEmailRegistered(driver.getEmail())) {
			return null;
		}

		driver.setPassword(passwordEncoder.encode(driver.getPassword()));
		List<Role> roles = roleService.findByName("ROLE_DRIVER");
		driver.setRoles(roles);
		driver.setActive(false);
		Vehicle vehicle = vehicleRepository.findById(driverDto.getVehicle()).get();
		if(vehicle == null) {
			return null;
		}
		driver.setVehicle(vehicle);
		return driverRepository.save(driver);
	}
	
	public Driver updateDriver(DriverDto driverDto, int id) {
		Driver driver = findOne(id);

		if (driver == null) {
			return null;
		}

		driver.setId(driverDto.getId());
		driver.setName(driverDto.getName());
		driver.setSurname(driverDto.getSurname());
		driver.setAddress(driverDto.getAddress());
		driver.setEmail(driverDto.getEmail());
		driver.setBlock(driverDto.isBlock());
		driver.setNumber(driverDto.getNumber());
		driver.setPicture(driverDto.getPicture());
		driver.setDocuments(driverDto.getDocuments());
		driver.setActive(driverDto.isActive());

		return driverRepository.save(driver);
	}
	
	public void remove(Integer id) {
		driverRepository.deleteById(id);
	}

	
	private void addPathToDtos(ArrayList<DrivingStatisticDto> dtos, Path path) {
		for (DrivingStatisticDto dto : dtos) {
			if (dto.getDate().compareTo(path.getStart()) == 0) {
				dto.setNumberOfDrivings(dto.getNumberOfDrivings() + 1);
				dto.setKilometersTravelled(dto.getKilometersTravelled() + path.getKilometers());
				dto.setMoney(dto.getMoney() + path.getPrice());
			}
		}
	}
	
	private void addDrivingToDtos(ArrayList<DrivingStatisticDto> dtos, Driving driving) {
		for(Path path: driving.getPaths()) {
			addPathToDtos(dtos, path);
		}
	}
	
	private void fillStatistics(ArrayList<DrivingStatisticDto> dtos, LocalDate start, LocalDate end) {
		while(start.isBefore(end.plusDays(1))) {
			DrivingStatisticDto dto = new DrivingStatisticDto(start, 0, 0, 0);
			dtos.add(dto);
			start = start.plusDays(1);
		}
	}
	
	public ArrayList<DrivingStatisticDto> getDrivingStatisticsForDriverAndDateRange(int driverId, LocalDate start, LocalDate end){
		ArrayList<DrivingStatisticDto> dtos = new ArrayList<DrivingStatisticDto>();
		fillStatistics(dtos, start, end);
		Driver driver = driverRepository.getOneWithDrivings(driverId);
		for(Driving driving : driver.getDrivings()) {
			addDrivingToDtos(dtos, driving);
		}
		return dtos;
	}
	
	public ArrayList<DrivingStatisticDto> getDrivingStatisticsForAllDriverAndDateRange(LocalDate start, LocalDate end) {
		ArrayList<DrivingStatisticDto> dtos = new ArrayList<DrivingStatisticDto>();
		fillStatistics(dtos, start, end);

		Set<Driver> drivers = driverRepository.getAllWithDrivings();
		for (Driver driver : drivers) {
			for (Driving driving : driver.getDrivings()) {
				addDrivingToDtos(dtos, driving);
			}
		}
		return dtos;
	}
	
	public boolean isEmailRegistered(String email) {
        // Ako postoji registrovan zadati mejl, vrati true, ako ne postoji, vrati false
        Driver driver = driverRepository.findByEmail(email);
        return driver != null;
    }
}
