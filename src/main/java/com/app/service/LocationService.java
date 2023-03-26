package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Location;
import com.app.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public Location findOne(Integer id) {
		return locationRepository.findById(id).orElseGet(null);
	}
	
	public List<Location> findAll(){
		return locationRepository.findAll();
	}
	
	public Location save(Location location) {
		return locationRepository.save(location);
	}
	
	public void remove(Integer id) {
		locationRepository.deleteById(id);
	}

}
