package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.DrivingStatisticDto;
import com.app.model.Driving;
import com.app.model.Passenger;
import com.app.model.Path;
import com.app.repository.PassengerRepository;

@Service
public class PassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	public Passenger findOne(Integer id) {
		return passengerRepository.findById(id).get();
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
	
	private void addPathToDtos(ArrayList<DrivingStatisticDto> dtos, Path path) {
		for (DrivingStatisticDto dto : dtos) {
			if (dto.getDate().compareTo(path.getStart()) == 0) {
				dto.setNumberOfDrivings(dto.getNumberOfDrivings() + 1);
				dto.setKilometersTravelled(dto.getKilometersTravelled() +  path.getKilometers());
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
	
	public ArrayList<DrivingStatisticDto> getDrivingStatisticsForPassengerAndDateRange(int passengerId, LocalDate start, LocalDate end){
		ArrayList<DrivingStatisticDto> dtos = new ArrayList<DrivingStatisticDto>();
		fillStatistics(dtos, start, end);
		Passenger passenger = passengerRepository.getOneWithDrivings(passengerId);
		for(Driving driving : passenger.getDrivings()) {
			addDrivingToDtos(dtos, driving);
		}
		return dtos;
	}
	
	public ArrayList<DrivingStatisticDto> getDrivingStatisticsForAllPassengerAndDateRange(LocalDate start, LocalDate end) {
		ArrayList<DrivingStatisticDto> dtos = new ArrayList<DrivingStatisticDto>();
		fillStatistics(dtos, start, end);

		Set<Passenger> passengers = passengerRepository.getAllWithDrivings();
		for (Passenger passenger : passengers) {
			for (Driving driving : passenger.getDrivings()) {
				addDrivingToDtos(dtos, driving);
			}
		}
		return dtos;
	}
	
}
