package com.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;

import com.app.model.Driving;
import com.app.model.Location;
import com.app.model.Path;

public class DrivingDto {
	private int id;
	private LocalDate start;
	private LocalDate end;
	private int price;

	private Location departure;
	private Location destination;
	
	public DrivingDto() {
		super();
	}
	
	public DrivingDto(Driving driving) {
		id = driving.getId();
		start = driving.getStart();
		end = driving.getEnd();
		price = driving.getPrice();
		ArrayList<Path> paths = new ArrayList<Path>(driving.getPaths());
		departure = paths.get(0).getDeparture();
		destination = paths.get(paths.size() - 1).getDestination();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Location getDeparture() {
		return departure;
	}

	public void setDeparture(Location departure) {
		this.departure = departure;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	

}
