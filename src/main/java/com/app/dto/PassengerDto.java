package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.model.Driving;
import com.app.model.Passenger;

public class PassengerDto {
	private int id;
	private String name;
	private String surname;
	private String picture;
	private String number;
	private String email;
	private String address;
	private boolean block;
	private Set<Driving> drivings = new HashSet<Driving>();
    
	public PassengerDto() {
		super();
	}

	public PassengerDto(int id, String name, String surname, String picture, String number, String email,
			String address, boolean block) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.picture = picture;
		this.number = number;
		this.email = email;
		this.address = address;
		this.block = block;
	}
	

	public PassengerDto(Passenger passenger) {
		super();
		id = passenger.getId();
		name = passenger.getName();
		surname = passenger.getSurname();
		picture = passenger.getPicture();
		number = passenger.getNumber();
		email = passenger.getEmail();
		address = passenger.getAddress();
		block = passenger.isBlock();
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

	public Set<Driving> getDrivings() {
		return drivings;
	}

	public void setDrivings(Set<Driving> drivings) {
		this.drivings = drivings;
	}

    
}
