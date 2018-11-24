package com.umg.usageapp.models;

import java.security.KeyStore.PrivateKeyEntry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name ="estatus")
public class Estatus {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_estatus")
	private int idEstatus;
	@Column (name = "nombre")
	private String nombre;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_aplicacion")
	@JsonBackReference
	private Aplicacion aplicacion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_evento")
	@JsonBackReference
	private Evento evento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sector_negocio")
	@JsonBackReference
	private Accion accion;
	
	public int getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Aplicacion getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Accion getAccion() {
		return accion;
	}
	public void setAccion(Accion accion) {
		this.accion = accion;
	}
	public Estatus(int idEstatus, String nombre, Aplicacion aplicacion, Evento evento, Accion accion) {
		super();
		this.idEstatus = idEstatus;
		this.nombre = nombre;
		this.aplicacion = aplicacion;
		this.evento = evento;
		this.accion = accion;
	}
	public Estatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
