package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Driver;
import com.app.repository.DriverRepository;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	public Driver findOne(Integer id) {
		return driverRepository.findById(id).orElseGet(null);
	}
	
	public List<Driver> findAll(){
		return driverRepository.findAll();
	}
	
	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}
	
	public void remove(Integer id) {
		driverRepository.deleteById(id);
	}

}
