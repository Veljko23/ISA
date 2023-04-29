package com.app.dto;

import com.app.model.Passenger;

public class PassengerDto {

	private String name;
	private String surname;
	private String email;
	
	public PassengerDto(Passenger passenger) {
		name = passenger.getName();
		surname = passenger.getSurname();
		email = passenger.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
