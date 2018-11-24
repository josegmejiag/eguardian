package com.umg.usageapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="bitacora")
public class Bitacora {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_bitacora")
	private int idBitacora;
	@Column (name = "fechaCreacion")
	private Date fechaCreacion;
	@Column (name = "traza")
	private String traza;
	@Column (name = "usuario")
	private String usuario;
	@Column (name = "telefono")
	private String telefono;
	
	
	
	public int getIdBitacora() {
		return idBitacora;
	}
	public void setIdBitacora(int idBitacora) {
		this.idBitacora = idBitacora;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getTraza() {
		return traza;
	}
	public void setTraza(String traza) {
		this.traza = traza;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Bitacora(int idBitacora, Date fechaCreacion, String traza, String usuario, String telefono) {
		super();
		this.idBitacora = idBitacora;
		this.fechaCreacion = fechaCreacion;
		this.traza = traza;
		this.usuario = usuario;
		this.telefono = telefono;
	}
	public Bitacora() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
