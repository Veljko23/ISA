package com.app.dto;

import com.app.model.Driver;

public class DriverDto {
	
	private String name;
	private String surname;
	private String picture;
	private String email;
	private String documents;
	private boolean active;
	private String vehicle;
	
	public DriverDto() {
		super();
	}
	
	public DriverDto(Driver driver) {
		name = driver.getName();
		surname = driver.getSurname();
		picture = driver.getPicture();
		email = driver.getEmail();
		documents = driver.getDocuments();
		active = driver.isActive();
		vehicle = driver.getVehicle().getModel();
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

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	
	
}
