package com.app.dto;

import com.app.model.Vehicle;
import com.app.model.VehicleType;

public class VehicleDTO {
	private int id;
	private VehicleType type;
	private boolean active;
	private LocationDTO location;

	public VehicleDTO() {
		super();
	}

	public VehicleDTO(int id, boolean active, LocationDTO location, VehicleType type) {
		super();
		this.id = id;
		this.active = active;
		this.location = location;
		this.type = type;
	}

	public VehicleDTO(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.active = vehicle.getDriver().isActive();
		this.location = new LocationDTO(vehicle.getCurrentLocation());
		this.type = vehicle.getVehicleType();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

}
