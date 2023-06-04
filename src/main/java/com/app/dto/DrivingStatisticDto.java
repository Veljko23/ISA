package com.app.dto;

import java.time.LocalDate;

public class DrivingStatisticDto {
	private LocalDate date;
	private int numberOfDrivings;
	private double kilometersTravelled;
	private double money;
	
	public DrivingStatisticDto() {
		super();
	}

	public DrivingStatisticDto(LocalDate date, int numberOfDrivings, double kilometersTravelled, double money) {
		super();
		this.date = date;
		this.numberOfDrivings = numberOfDrivings;
		this.kilometersTravelled = kilometersTravelled;
		this.money = money;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNumberOfDrivings() {
		return numberOfDrivings;
	}

	public void setNumberOfDrivings(int numberOfDrivings) {
		this.numberOfDrivings = numberOfDrivings;
	}

	public double getKilometersTravelled() {
		return kilometersTravelled;
	}

	public void setKilometersTravelled(double kilometersTravelled) {
		this.kilometersTravelled = kilometersTravelled;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	
}
