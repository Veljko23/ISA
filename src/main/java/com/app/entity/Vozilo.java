package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity
public class Vozilo {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy = "vozilo")
	private Vozac vozac;
	@Column
	private String model;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tip_vozila_id")
	private TipVozila tipVozila;
	@Column
	private String tablice;
	@Column
	private int brojMesta;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lokacija_id")
	private Lokacija trenutnaLokacija;
	
	public Vozilo() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vozac getVozac() {
		return vozac;
	}

	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public TipVozila getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(TipVozila tipVozila) {
		this.tipVozila = tipVozila;
	}

	public String getTablice() {
		return tablice;
	}

	public void setTablice(String tablice) {
		this.tablice = tablice;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Lokacija getTrenutnaLokacija() {
		return trenutnaLokacija;
	}

	public void setTrenutnaLokacija(Lokacija trenutnaLokacija) {
		this.trenutnaLokacija = trenutnaLokacija;
	}

	
}
