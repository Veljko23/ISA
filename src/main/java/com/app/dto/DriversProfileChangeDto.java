package com.app.dto;

import com.app.model.DriversProfileChange;

public class DriversProfileChangeDto {

	private int id;
	private String name;
	private String surname;
	private String picture;
	private String number;
	private String email;
	private String address;
	private String documents;
	//private boolean active;
	private int driverId;

	public DriversProfileChangeDto() {
		super();
	}

	public DriversProfileChangeDto(DriversProfileChange change) {
		id = change.getId();
		name = change.getName();
		surname = change.getSurname();
		picture = change.getPicture();
		number = change.getNumber();
		email = change.getEmail();
		address = change.getAddress();
		documents = change.getDocuments();
		//active = change.isActive();
		driverId = change.getDriver().getId();
	}

	public DriversProfileChange toDriverProfileChange() {
		DriversProfileChange change = new DriversProfileChange();
		change.setId(id);
		change.setName(name);
		change.setSurname(surname);
		change.setPicture(picture);
		change.setNumber(number);
		change.setEmail(email);
		change.setAddress(address);
		change.setDocuments(documents);
		//change.setActive(active);
		return change;
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

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

//	public boolean isActive() {
//		return active;
//	}
//
//	public void setActive(boolean active) {
//		this.active = active;
//	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

}
