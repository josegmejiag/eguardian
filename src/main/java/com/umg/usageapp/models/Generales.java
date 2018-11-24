package com.umg.usageapp.models;

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
@Table(name ="generales")
public class Generales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_generales")
	private int idGenerales;
	@Column (name = "nombreApp")
	private String nombreApp;
	@Column (name = "contacto")
	private String contacto;
	@Column (name = "urlApi")
	private String urlApi ;
	@Column (name = "imgTuto01")
	private String imgTuto01;
	@Column (name = "imgTuto02")
	private String imgTuto02;
	@Column (name = "imgTuto03")
	private String imgTuto03;
	@Column (name = "imgTuto04")
	private String imgTuto04;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estatus")
	@JsonBackReference
	private Estatus estatus;
	
	
	public int getIdGenerales() {
		return idGenerales;
	}
	public void setIdGenerales(int idGenerales) {
		this.idGenerales = idGenerales;
	}
	public String getNombreApp() {
		return nombreApp;
	}
	public void setNombreApp(String nombreApp) {
		this.nombreApp = nombreApp;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getUrlApi() {
		return urlApi;
	}
	public void setUrlApi(String urlApi) {
		this.urlApi = urlApi;
	}
	public String getImgTuto01() {
		return imgTuto01;
	}
	public void setImgTuto01(String imgTuto01) {
		this.imgTuto01 = imgTuto01;
	}
	public String getImgTuto02() {
		return imgTuto02;
	}
	public void setImgTuto02(String imgTuto02) {
		this.imgTuto02 = imgTuto02;
	}
	public String getImgTuto03() {
		return imgTuto03;
	}
	public void setImgTuto03(String imgTuto03) {
		this.imgTuto03 = imgTuto03;
	}
	public String getImgTuto04() {
		return imgTuto04;
	}
	public void setImgTuto04(String imgTuto04) {
		this.imgTuto04 = imgTuto04;
	}
	public Estatus getEstatus() {
		return estatus;
	}
	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}
	public Generales(int idGenerales, String nombreApp, String contacto, String urlApi, String imgTuto01,
			String imgTuto02, String imgTuto03, String imgTuto04, Estatus estatus) {
		super();
		this.idGenerales = idGenerales;
		this.nombreApp = nombreApp;
		this.contacto = contacto;
		this.urlApi = urlApi;
		this.imgTuto01 = imgTuto01;
		this.imgTuto02 = imgTuto02;
		this.imgTuto03 = imgTuto03;
		this.imgTuto04 = imgTuto04;
		this.estatus = estatus;
	}
	public Generales() {
		super();
		// TODO Auto-generated constructor stub
	}



}
