package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Driver;
import com.app.model.Driving;

public class DriverWithDrivingsDto {
	private String name;
	private String surname;
	private String picture;
	private String email;
	private String documents;
	private boolean active;
	private List<DrivingDto> drivings = new ArrayList<DrivingDto>();
	public DriverWithDrivingsDto(Driver driver) {
		this.name = driver.getName();
		this.surname = driver.getSurname();
		this.picture = driver.getPicture();
		this.email = driver.getEmail();
		this.documents = driver.getDocuments();
		this.active = driver.isActive();
		for(Driving driving : driver.getDrivings()) {
			drivings.add(new DrivingDto(driving));
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocuments() {
		return documents;
	}
	public void setDocuments(String documents) {
		this.documents = documents;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<DrivingDto> getDrivings() {
		return drivings;
	}
	public void setDrivings(List<DrivingDto> drivings) {
		this.drivings = drivings;
	}
	
	
}
