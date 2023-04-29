package com.app.dto;

import java.time.LocalDate;


import com.app.model.Location;
import com.app.model.Path;

public class PathDto {
	
	private int id;
	private LocalDate start;
	private LocalDate end;
	private Location departure;
	private Location destination;
	private double kilometers;
	
	public PathDto() {
		super();
	}
	
	
	public PathDto(Path path) {
		id = path.getId();
		start = path.getStart();
		end = path.getEnd();
		departure = path.getDeparture();
		destination = path.getDestination();
		kilometers = path.getKilometers();
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
	public double getKilometers() {
		return kilometers;
	}
	public void setKilometers(double kilometers) {
		this.kilometers = kilometers;
	}

}
