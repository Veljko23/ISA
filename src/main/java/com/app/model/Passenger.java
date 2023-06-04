package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("P")
public class Passenger extends User{
	
	@ManyToMany(mappedBy="passengers")
	private Set<Driving> drivings = new HashSet<Driving>();
	
	
	public Passenger() {}

	public Set<Driving> getDrivings() {
		return drivings;
	}


	public void setDrivings(Set<Driving> drivings) {
		this.drivings = drivings;
	}

	
	
}
