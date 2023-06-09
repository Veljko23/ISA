package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("D")
public class Driver extends User{
	
	
	@Column
	private String documents;
	@Column
	private boolean active;
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Driving> drivings = new HashSet<Driving>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	public Driver() {
		super();
	}

	public Driver(String documents, boolean active, Set<Driving> drivings, Vehicle vehicle) {
		super();
		this.documents = documents;
		this.active = active;
		this.drivings = drivings;
		this.vehicle = vehicle;
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

	public Set<Driving> getDrivings() {
		return drivings;
	}

	public void setDrivings(Set<Driving> drivings) {
		this.drivings = drivings;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	
}
