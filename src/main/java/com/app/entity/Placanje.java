package com.app.entity;

import java.sql.Date;
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

import com.app.enums.TipPlacanja;
@Table
@Entity
public class Placanje {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private TipPlacanja tipPlacanja;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "putnik_id")
	private Putnik putnik;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "placanje_voznje", joinColumns = @JoinColumn(name = "voznja_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "placanje_id", referencedColumnName = "id"))
	private Set<Putanja> putanje = new HashSet<Putanja>();
	
	@Column
	private Date vremePlacanja;
	@Column
	private double cena;
	
	public Placanje() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipPlacanja getTipPlacanja() {
		return tipPlacanja;
	}

	public void setTipPlacanja(TipPlacanja tipPlacanja) {
		this.tipPlacanja = tipPlacanja;
	}

	public Putnik getPutnik() {
		return putnik;
	}

	public void setPutnik(Putnik putnik) {
		this.putnik = putnik;
	}

	public Set<Putanja> getPutanje() {
		return putanje;
	}

	public void setPutanje(Set<Putanja> putanje) {
		this.putanje = putanje;
	}

	public Date getVremePlacanja() {
		return vremePlacanja;
	}

	public void setVremePlacanja(Date vremePlacanja) {
		this.vremePlacanja = vremePlacanja;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	

}
