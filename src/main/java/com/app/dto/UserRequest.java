package com.app.dto;

import com.app.model.User;

public class UserRequest {
	
	private int id;
	private String name;
	private String surname;
	private String picture;
	private String number;
	private String email;
	private String address;
	private String password;
	private boolean block;
	
	public UserRequest() {
		super();
	}
	
	public UserRequest(User user) {
		id = user.getId();
		name = user.getName();
		surname = user.getSurname();
		picture = user.getPicture();
		number = user.getNumber();
		email = user.getEmail();
		address = user.getAddress();
		block = user.isBlock();
	}
	
	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setSurname(surname);
		user.setPicture(picture);
		user.setNumber(number);
		user.setEmail(email);
		user.setAddress(address);
		user.setPassword(password);
		user.setBlock(false);
		
		return user;
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

}
