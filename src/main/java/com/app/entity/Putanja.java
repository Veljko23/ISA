package com.app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Table
@Entity
public class Putanja {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Date pocetak;
	@Column
	private Date kraj;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "polaziste_id")
	private Lokacija polaziste;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "odrediste_id")
	private Lokacija odrediste;
	@Column
	private double kilometraza;
	@Column
	private String procenjenoVreme;
	@Column
	private double cena;
	
	public Putanja() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public Lokacija getPolaziste() {
		return polaziste;
	}

	public void setPolaziste(Lokacija polaziste) {
		this.polaziste = polaziste;
	}

	public Lokacija getOdrediste() {
		return odrediste;
	}

	public void setOdrediste(Lokacija odrediste) {
		this.odrediste = odrediste;
	}

	public double getKilometraza() {
		return kilometraza;
	}

	public void setKilometraza(double kilometraza) {
		this.kilometraza = kilometraza;
	}

	public String getProcenjenoVreme() {
		return procenjenoVreme;
	}

	public void setProcenjenoVreme(String procenjenoVreme) {
		this.procenjenoVreme = procenjenoVreme;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	
}
