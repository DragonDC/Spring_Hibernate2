package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="UkladPlanetarny")
@NamedQueries({
	@NamedQuery(name = "uklady.all", query = "Select u from UkladPlanetarny u"),
	@NamedQuery(name = "uklady.byId", query = "Select u from UkladPlanetarny u where u.id = :id"),
	@NamedQuery(name = "uklady.byLiczbaObiektow", query = "Select u from UkladPlanetarny u where u.liczbaObiektow = :liczbaObiektow")
})
public class UkladPlanetarny {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private String nazwaUkladu;
    private Integer liczbaObiektow;
    private List<Planeta> planety = new ArrayList<Planeta>();
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNazwaUkladu() {
		return nazwaUkladu;
	}
	public void setNazwaUkladu(String nazwaUkladu) {
		this.nazwaUkladu = nazwaUkladu;
	}
	
	public int getLiczbaObiektow() {
		return liczbaObiektow;
	}
	public void setLiczbaObiektow(int liczbaObiektow) {
		this.liczbaObiektow = liczbaObiektow;
	}
	
	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Planeta> getPlanety() {
		return planety;
	}
	public void setPlanety(List<Planeta> planety) {
		this.planety = planety;
	}

}










