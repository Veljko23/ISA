package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Driving;
import com.app.repository.DrivingRepository;

@Service
public class DrivingService {

	@Autowired
	private DrivingRepository drivingRepository;
	
	public Driving findOne(Integer id) {
		return drivingRepository.findById(id).orElseGet(null);
	}
	
	public List<Driving> findAll(){
		return drivingRepository.findAll();
	}
	
	public Driving save(Driving driving) {
		return drivingRepository.save(driving);
	}
	
	public void remove(Integer id) {
		drivingRepository.deleteById(id);
	}
}
