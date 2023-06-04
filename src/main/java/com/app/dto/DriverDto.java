package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.Driver;
import com.app.model.Driving;

public class DriverDto {
	
	private int id;
	private String name;
	private String surname;
	private String picture;
	private String number;
	private String email;
	private String address;
	private String password;
	private boolean block;
	private Set<Driving> drivings;
	private String documents;
	private boolean active;
	private String vehicle;
	
	public DriverDto() {}
	
	public DriverDto(int id, String name, String surname, String picture, String number, String email, String address,
			String password, boolean block, String documents, boolean active, String vehicle) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.picture = picture;
		this.number = number;
		this.email = email;
		this.address = address;
		this.password = password;
		this.block = block;
		this.documents = documents;
		this.active = active;
		this.vehicle = vehicle;
	}

	public DriverDto(Driver driver) {
		id = driver.getId();
		name = driver.getName();
		surname = driver.getSurname();
		picture = driver.getPicture();
		number = driver.getNumber();
		email = driver.getEmail();
		address = driver.getAddress();
		password = driver.getPassword();
		block = driver.isBlock();
		documents = driver.getDocuments();
		active = driver.isActive();
		vehicle = driver.getVehicle().getModel();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public Set<Driving> getDrivings() {
		return drivings;
	}

	public void setDrivings(Set<Driving> drivings) {
		this.drivings = drivings;
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
