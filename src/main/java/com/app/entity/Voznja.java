package com.app.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.enums.StatusVoznje;

@Table
@Entity
public class Voznja {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private Date pocetak;
	@Column
	private Date kraj;
	@Column
	private int cena;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vozac_id")
	private Vozac vozac;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "voznja_putnik", joinColumns = @JoinColumn(name = "voznja_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "putnik_id", referencedColumnName = "id"))
	private Set<Putnik> putnici = new HashSet<Putnik>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Putanja> putanje = new HashSet<Putanja>();
	@Column
	private String procenjenoVreme;
	@Column
	private StatusVoznje statusVoznje;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tip_vozila_id")
	private TipVozila tipVozila;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "placanje_voznje", joinColumns = @JoinColumn(name = "voznja_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "placanje_id", referencedColumnName = "id"))
	private Set<Placanje> placanja = new HashSet<Placanje>();

	public Voznja() {}

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

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Vozac getVozac() {
		return vozac;
	}

	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}

	public Set<Putnik> getPutnici() {
		return putnici;
	}

	public void setPutnici(Set<Putnik> putnici) {
		this.putnici = putnici;
	}

	public Set<Putanja> getPutanje() {
		return putanje;
	}

	public void setPutanje(Set<Putanja> putanje) {
		this.putanje = putanje;
	}

	public String getProcenjenoVreme() {
		return procenjenoVreme;
	}

	public void setProcenjenoVreme(String procenjenoVreme) {
		this.procenjenoVreme = procenjenoVreme;
	}

	public StatusVoznje getStatusVoznje() {
		return statusVoznje;
	}

	public void setStatusVoznje(StatusVoznje statusVoznje) {
		this.statusVoznje = statusVoznje;
	}

	public TipVozila getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(TipVozila tipVozila) {
		this.tipVozila = tipVozila;
	}

	public Set<Placanje> getPlacanja() {
		return placanja;
	}

	public void setPlacanja(Set<Placanje> placanja) {
		this.placanja = placanja;
	}

	
}
