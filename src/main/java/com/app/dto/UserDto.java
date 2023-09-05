package com.app.dto;

import com.app.model.User;

public class UserDto {
	
	private int id;
	private String name;
	private String surname;
	private String picture;
	private String number;
	private String email;
	private String address;
	private String role;
	private boolean block;
	
	public UserDto() {}
	
	public UserDto(int id, String name, String surname, String picture, String number, String email, String address,
			boolean block) {
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
	
	public UserDto(User user) {
		id = user.getId();
		name = user.getName();
		surname = user.getSurname();
		picture = user.getPicture();
		number = user.getNumber();
		email = user.getEmail();
		address = user.getAddress();
		role = user.getRoles().get(0).getName().toString();
		block = user.isBlock();
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}
	
	
}
