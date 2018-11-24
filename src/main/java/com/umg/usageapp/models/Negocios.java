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
@Table(name ="negocios")
public class Negocios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_negocio")
	private int idNEgocio;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empresa")
	@JsonBackReference
	private Empresa empresa;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sector_negocio")
	@JsonBackReference
	private SectorNegocio sectornegocio;
	@Column (name = "fecha")
	private Date fecha;
	@Column (name = "descripcion")
	private String descripcion;
	
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public SectorNegocio getSectornegocio() {
		return sectornegocio;
	}
	public void setSectornegocio(SectorNegocio sectornegocio) {
		this.sectornegocio = sectornegocio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdNEgocio() {
		return idNEgocio;
	}
	public void setIdNEgocio(int idNEgocio) {
		this.idNEgocio = idNEgocio;
	}
	public Negocios(int idNEgocio, Empresa empresa, SectorNegocio sectornegocio, Date fecha, String descripcion) {
		super();
		this.idNEgocio = idNEgocio;
		this.empresa = empresa;
		this.sectornegocio = sectornegocio;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	public Negocios() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
