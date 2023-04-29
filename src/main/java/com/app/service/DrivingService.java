package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.DrivingSortDto;
import com.app.model.Driving;
import com.app.model.User;
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
	
	public List<Driving> findByOrderByStartAsc(){
		return drivingRepository.findByOrderByStartAsc();
	}
	
	public List<Driving> getPassengersDrivingHistory(User loggedUser, DrivingSortDto drivingSortDto) {
		if(drivingSortDto.getField().equals("price")) {
			if(drivingSortDto.getOrder().equals("asc")) {
				return drivingRepository.findAllForPassengerOrderByPriceAsc(loggedUser.getId());
			}else if(drivingSortDto.getOrder().equals("desc")) {
				return drivingRepository.findAllForPassengerOrderByPriceDesc(loggedUser.getId());
			}
		}else if(drivingSortDto.getField().equals("start")) {
			if(drivingSortDto.getOrder().equals("asc")) {
				return drivingRepository.findAllForPassengerOrderByStartAsc(loggedUser.getId());
			}else if(drivingSortDto.getOrder().equals("desc")) {
				return drivingRepository.findAllForPassengerOrderByStartDesc(loggedUser.getId());
			}
		}else if(drivingSortDto.getField().equals("end")) {
			if(drivingSortDto.getOrder().equals("asc")) {
				return drivingRepository.findAllForPassengerOrderByEndAsc(loggedUser.getId());
			}else if(drivingSortDto.getOrder().equals("desc")) {
				return drivingRepository.findAllForPassengerOrderByEndDesc(loggedUser.getId());
			}
		}
		return null;
	}
	
	public List<Driving> getDriversDrivingHistory(User loggedUser, DrivingSortDto drivingSortDto) {
		if(drivingSortDto.getField().equals("price")) {
			if(drivingSortDto.getOrder().equals("asc")) {
				return drivingRepository.findAllForDriverOrderByPriceAsc(loggedUser.getId());
			}else if(drivingSortDto.getOrder().equals("desc")) {
				return drivingRepository.findAllForDriverOrderByPriceDesc(loggedUser.getId());
			}
		}else if(drivingSortDto.getField().equals("start")) {
			if(drivingSortDto.getOrder().equals("asc")) {
				return drivingRepository.findAllForDriverOrderByStartAsc(loggedUser.getId());
			}else if(drivingSortDto.getOrder().equals("desc")) {
				return drivingRepository.findAllForDriverOrderByStartDesc(loggedUser.getId());
			}
		}else if(drivingSortDto.getField().equals("end")) {
			if(drivingSortDto.getOrder().equals("asc")) {
				return drivingRepository.findAllForDriverOrderByEndAsc(loggedUser.getId());
			}else if(drivingSortDto.getOrder().equals("desc")) {
				return drivingRepository.findAllForDriverOrderByEndDesc(loggedUser.getId());
			}
		}
		return null;
	}
	
	public Driving getOneDrivingWithPaths(int id) {
		return drivingRepository.findOneWithPaths(id);
	}
	
	public Driving getOneDrivingWithPathsAndPassengers(int id) {
		return drivingRepository.getOneWithPathsAndPassengers(id);
	}
	
	
}
