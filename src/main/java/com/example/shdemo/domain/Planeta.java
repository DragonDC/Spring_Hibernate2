package com.example.shdemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Planeta")
@NamedQueries({
	@NamedQuery(name = "planeta.all", query = "Select p from Planeta p"),
	@NamedQuery(name = "planeta.byId", query = "Select p from Planeta p where p.id = :id"),
	@NamedQuery(name = "planeta.bySrednica", query = "Select p from Planeta p where p.srednica = :srednica")
})
public class Planeta {
	
	
	private Long id;		
	private String nazwa;
	private int srednica;
	
	@Column(name="ilosc_ksiezycow")
	private int il_ks;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public int getSrednica() {
		return srednica;
	}
	
	public void setSrednica(int srednica) {
		this.srednica = srednica;
	}
	
	public int getIl_ks() {
		return il_ks;
	}
	public void setIl_ks(int il_ks) {
		this.il_ks = il_ks;
	}
	
}