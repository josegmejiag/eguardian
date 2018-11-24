package com.umg.usageapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="accion")
public class Accion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_accion")
	private int idAccion;
	@Column (name = "nombre")
	private String nombre;
	
	
	
	public int getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Accion(int idAccion, String nombre) {
		super();
		this.idAccion = idAccion;
		this.nombre = nombre;
	}
	public Accion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
