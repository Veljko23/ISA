package com.app.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Table
@Entity
public class Putnik {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "voznja_putnik", joinColumns = @JoinColumn(name = "voznja_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "putnik_id", referencedColumnName = "id"))
	private Set<Voznja> voznje = new HashSet<Voznja>();
	
	
	public Putnik() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Set<Voznja> getVoznje() {
		return voznje;
	}


	public void setVoznje(Set<Voznja> voznje) {
		this.voznje = voznje;
	}

	
	
}
