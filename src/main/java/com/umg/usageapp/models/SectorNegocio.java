package com.umg.usageapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="sector_negocio")
public class SectorNegocio {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_sector_negocio")
	private int idSectorNegocio;
	@Column (name = "nombre")
	private String nombre;
	
	
	public int getIdSectorNegocio() {
		return idSectorNegocio;
	}
	public void setIdSectorNegocio(int idSectorNegocio) {
		this.idSectorNegocio = idSectorNegocio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public SectorNegocio(int idSectorNegocio, String nombre) {
		super();
		this.idSectorNegocio = idSectorNegocio;
		this.nombre = nombre;
	}
	public SectorNegocio() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
