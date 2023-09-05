package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.DriversProfileChangeDto;
import com.app.enums.DriversProfileChangeStatus;
import com.app.model.Driver;
import com.app.model.DriversProfileChange;
import com.app.repository.DriverRepository;
import com.app.repository.DriversProfileChangeRepository;

@Service
public class DriversProfileChangeService {

	@Autowired
	DriversProfileChangeRepository driversProfileChangeRepository;
	@Autowired
	DriverRepository driverRepository;
	
	public List<DriversProfileChange> findAll(){
		return driversProfileChangeRepository.findAll();
	}
	
	public DriversProfileChange findOne(Integer id) {
		return driversProfileChangeRepository.findById(id).orElseGet(null);
	}
	
	public DriversProfileChange requestProfileChange(DriversProfileChangeDto dto) {
		DriversProfileChange profileChange = dto.toDriverProfileChange();
		Driver driver = driverRepository.findById(dto.getDriverId()).get();
		profileChange.setDriver(driver);
		profileChange.setStatus(DriversProfileChangeStatus.OnWait);
		driversProfileChangeRepository.save(profileChange);
		return profileChange;
    }
	
	public DriversProfileChange acceptRequestProfileChange(int id) {
        DriversProfileChange profileChange = driversProfileChangeRepository.findById(id).get();
        profileChange.setStatus(DriversProfileChangeStatus.Accepted);
        driversProfileChangeRepository.save(profileChange);
        
        Driver driver = profileChange.getDriver();
        driver.setName(profileChange.getName());
        driver.setSurname(profileChange.getSurname());
        driver.setPicture(profileChange.getPicture());
        driver.setNumber(profileChange.getNumber());
        driver.setEmail(profileChange.getEmail());
        driver.setAddress(profileChange.getAddress());
        driver.setDocuments(profileChange.getDocuments());
        //driver.setActive(profileChange.isActive());
        driverRepository.save(driver);
        return profileChange;
    }
	
	public DriversProfileChange denyRequestProfileChange(int id) {
		DriversProfileChange profileChange = driversProfileChangeRepository.findById(id).get();
		profileChange.setStatus(DriversProfileChangeStatus.Denied);
		driversProfileChangeRepository.save(profileChange);
		return profileChange;
    }
	
	public DriversProfileChange getProfileChangeById(int id) {
        return driversProfileChangeRepository.findById(id).orElse(null);
    }
}
