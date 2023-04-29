package com.app.model;

import java.time.LocalDate;
import java.util.HashSet;
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
import javax.persistence.Table;

import com.app.enums.PaymentType;
@Table
@Entity
public class Payment {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private PaymentType paymentType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pass_id")
	private Passenger passenger;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "payment_driving", joinColumns = @JoinColumn(name = "driving_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "payment_id", referencedColumnName = "id"))
	private Set<Path> paths = new HashSet<Path>();
	
	@Column
	private LocalDate paymentTime;
	@Column
	private double price;
	
	public Payment() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Set<Path> getPaths() {
		return paths;
	}

	public void setPaths(Set<Path> paths) {
		this.paths = paths;
	}

	public LocalDate getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDate paymentTime) {
		this.paymentTime = paymentTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

}
