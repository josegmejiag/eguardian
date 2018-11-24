package com.umg.usageapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="aplicacion")
public class Aplicacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_aplicacion")
	private int idAplicacion;
	@Column (name = "nombre")
	private String nombre;
	@Column (name = "tipo")
	private String tipo;
	@Column (name = "icono")
	private String icono;
	
	
	public int getIdAplicacion() {
		return idAplicacion;
	}
	public void setIdAplicacion(int idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public Aplicacion(int idAplicacion, String nombre, String tipo, String icono) {
		super();
		this.idAplicacion = idAplicacion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.icono = icono;
	}
	public Aplicacion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
