package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Passenger;
import com.app.repository.PassengerRepository;

@Service
public class PassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	public Passenger findOne(Integer id) {
		return passengerRepository.findById(id).orElseGet(null);
	}
	
	public List<Passenger> findAll(){
		return passengerRepository.findAll();
	}
	
	public Passenger save(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	public void remove(Integer id) {
		passengerRepository.deleteById(id);
	}

}
