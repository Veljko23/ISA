package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Table(name = "radno_vreme")
@Entity
public class RadnoVreme {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String pocetak;
	@Column
	private String kraj;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vozac_id", referencedColumnName = "id")
	private Vozac vozac;
	
	public RadnoVreme() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public Vozac getVozac() {
		return vozac;
	}

	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}
	
	

}
