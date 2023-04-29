package com.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.model.Driving;
import com.app.model.Passenger;
import com.app.model.Path;


public class DrivingWithPathsAndPassengersDto {
	
	private LocalDate start;
	private LocalDate end;
	private int price;
	private DriverDto driver;
	private List<PassengerDto> passengers = new ArrayList<PassengerDto>();
	private List<PathDto> paths = new ArrayList<PathDto>();
	
	public DrivingWithPathsAndPassengersDto(Driving driving) {
		start = driving.getStart();
		end = driving.getEnd();
		price = driving.getPrice();
		driver = new DriverDto(driving.getDriver());
		for(Passenger passenger: driving.getPassengers()) {
			passengers.add(new PassengerDto(passenger));
		}
		for(Path path : driving.getPaths()) {
            paths.add(new PathDto(path));
        }
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public DriverDto getDriver() {
		return driver;
	}

	public void setDriver(DriverDto driver) {
		this.driver = driver;
	}

	public List<PassengerDto> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerDto> passengers) {
		this.passengers = passengers;
	}

	public List<PathDto> getPaths() {
		return paths;
	}

	public void setPaths(List<PathDto> paths) {
		this.paths = paths;
	}
	

}
