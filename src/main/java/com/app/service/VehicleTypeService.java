package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.VehicleType;
import com.app.repository.VehicleTypeRepository;

@Service
public class VehicleTypeService {

	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	public VehicleType findOne(Integer id) {
		return vehicleTypeRepository.findById(id).orElseGet(null);
	}
	
	public List<VehicleType> findAll(){
		return vehicleTypeRepository.findAll();
	}
	
	public VehicleType save(VehicleType vehicleType) {
		return vehicleTypeRepository.save(vehicleType);
	}
	
	public void remove(Integer id) {
		vehicleTypeRepository.deleteById(id);
	}
}
