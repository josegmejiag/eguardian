package com.umg.usageapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="catalogo_puestos")
public class CatalogoPuestos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_catalogo_puestos")
	private int idCatalogoPuestos;
	@Column (name = "nombre")
	private String nombre;
	public int getIdCatalogoPuestos() {
		return idCatalogoPuestos;
	}
	public void setIdCatalogoPuestos(int idCatalogoPuestos) {
		this.idCatalogoPuestos = idCatalogoPuestos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
