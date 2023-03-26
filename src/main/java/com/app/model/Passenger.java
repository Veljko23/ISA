package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("P")
public class Passenger extends User{
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "driving_pass", joinColumns = @JoinColumn(name = "driving_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pass_id", referencedColumnName = "id"))
	private Set<Driving> drivings = new HashSet<Driving>();
	
	
	public Passenger() {}

	public Set<Driving> getDrivings() {
		return drivings;
	}


	public void setDrivings(Set<Driving> drivings) {
		this.drivings = drivings;
	}

	
	
}
