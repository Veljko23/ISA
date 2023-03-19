package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class Lokacija {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private double geoDuzina;
	@Column
	private double geoSirina;
	
	public Lokacija() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGeoDuzina() {
		return geoDuzina;
	}

	public void setGeoDuzina(double geoDuzina) {
		this.geoDuzina = geoDuzina;
	}

	public double getGeoSirina() {
		return geoSirina;
	}

	public void setGeoSirina(double geoSirina) {
		this.geoSirina = geoSirina;
	}

	
	
	
}
