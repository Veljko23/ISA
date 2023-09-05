package com.app.dto;

import com.app.model.Location;

public class LocationDTO {

	private int id;

	private double longitude;

	private double latitude;

	public LocationDTO() {
		super();
	}

	public LocationDTO(int id, double longitude, double latitude) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public LocationDTO(Location currentLocation) {
		this.id = currentLocation.getId();
		this.longitude = currentLocation.getLongitude();
		this.latitude = currentLocation.getLatitude();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
