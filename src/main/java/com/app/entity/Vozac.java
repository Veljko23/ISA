package com.app.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Table
@Entity
public class Vozac  {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String dokumenti;
	@Column
	private boolean aktivan;
	@OneToMany(mappedBy = "vozac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Voznja> voznje = new HashSet<Voznja>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vozilo_id", referencedColumnName = "id")
	private Vozilo vozilo;

	public Vozac() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDokumenti() {
		return dokumenti;
	}

	public void setDokumenti(String dokumenti) {
		this.dokumenti = dokumenti;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Set<Voznja> getVoznje() {
		return voznje;
	}

	public void setVoznje(Set<Voznja> voznje) {
		this.voznje = voznje;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	
}
