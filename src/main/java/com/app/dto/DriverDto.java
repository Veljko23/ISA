package com.app.dto;

import com.app.model.Driver;

public class DriverDto {
	
	private int id;
	private String name;
	private String surname;
	private String picture;
	private String number;
	private String email;
	private String address;
	private boolean block;
	private String documents;
	private String password;	
	private boolean active;
	private int vehicle;
	
	public DriverDto() {}
	
	public DriverDto(int id, String name, String surname, String picture, String number, String email, String address,
			 boolean block, String documents, boolean active, int vehicle) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.picture = picture;
		this.number = number;
		this.email = email;
		this.address = address;
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
		block = driver.isBlock();
		documents = driver.getDocuments();
		active = driver.isActive();
	}
	
	public Driver toDriver() {
		Driver driver = new Driver();
		driver.setId(id);
		driver.setName(name);
		driver.setSurname(surname);
		driver.setPicture(picture);
		driver.setNumber(number);
		driver.setEmail(email);
		driver.setAddress(address);
		driver.setBlock(false);
		driver.setDocuments(documents);
		driver.setActive(false);
		driver.setPassword(password);
		
		return driver;
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

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getVehicle() {
		return vehicle;
	}

	public void setVehicle(int vehicle) {
		this.vehicle = vehicle;
	}

}
