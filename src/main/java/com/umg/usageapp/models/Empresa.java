package com.umg.usageapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="empresa")
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_empresa")
	private int id;
	@Column (name = "nombre")
	private String nombre;
	@Column (name = "direccion")
	private String direccion;
	@Column (name = "numero_colaboradores")
	private int numeroColaboradores;
	@Column (name = "telefono")
	private String telefono;
	@Column (name = "logo")
	private String logo;
	@Column (name = "descripcion")
	private String descripcion;
	@Column (name = "status")
	private int status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getNumeroColaboradores() {
		return numeroColaboradores;
	}
	public void setNumeroColaboradores(int numeroColaboradores) {
		this.numeroColaboradores = numeroColaboradores;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Empresa(int id, String nombre, String direccion, int numeroColaboradores, String telefono, String logo,
			String descripcion, int status) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroColaboradores = numeroColaboradores;
		this.telefono = telefono;
		this.logo = logo;
		this.descripcion = descripcion;
		this.status = status;
	}
	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
