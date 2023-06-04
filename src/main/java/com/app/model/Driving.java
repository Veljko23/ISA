package com.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.app.enums.DrivingStatus;

@Table
@Entity
public class Driving {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private LocalDate start;
	@Column
	private LocalDate end;
	@Column
	private int price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "driver_id")
	private Driver driver;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "driving_pass", joinColumns = @JoinColumn(name = "driving_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pass_id", referencedColumnName = "id"))
	private Set<Passenger> passengers = new HashSet<Passenger>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Path> paths = new ArrayList<Path>();
	@Column
	private String estimatedTime;
	@Column
	private DrivingStatus drivingStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicle_type_id")
	private VehicleType vehicleType;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "payment_drive", joinColumns = @JoinColumn(name = "drive_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "payment_id", referencedColumnName = "id"))
	private Set<Payment> payments = new HashSet<Payment>();

	public Driving() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int cena) {
		this.price = cena;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Set<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public DrivingStatus getDrivingStatus() {
		return drivingStatus;
	}

	public void setDrivingStatus(DrivingStatus drivingStatus) {
		this.drivingStatus = drivingStatus;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	
}
