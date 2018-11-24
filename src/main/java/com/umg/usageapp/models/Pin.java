package com.umg.usageapp.models;

import java.util.Date;

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
@Table(name ="pin")
public class Pin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_pin")
	private int idPin;
	@Column (name = "ping")
	private int pin;
	@Column (name = "expiracion")
	private Date espiracion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	@JsonBackReference
	private User usuario;
	public int getIdPin() {
		return idPin;
	}
	public void setIdPin(int idPin) {
		this.idPin = idPin;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public Date getEspiracion() {
		return espiracion;
	}
	public void setEspiracion(Date espiracion) {
		this.espiracion = espiracion;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public Pin(int pin, Date espiracion, User usuario) {
		super();
		this.pin = pin;
		this.espiracion = espiracion;
		this.usuario = usuario;
	}
	public Pin(int idPin, int pin, Date espiracion, User usuario) {
		super();
		this.idPin = idPin;
		this.pin = pin;
		this.espiracion = espiracion;
		this.usuario = usuario;
	}
	public Pin() {
	}
	
	
	
	
	
	

}
