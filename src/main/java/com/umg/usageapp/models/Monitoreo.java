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
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name ="monitoreo")


@NamedNativeQuery(
        name = "updateIncidencia",
        query = "UPDATE prueba.monitoreo SET fecha_fin=:fechaFin , duracion = :duracion  WHERE id_monitoreo= :idMonitoreo"
    )





public class Monitoreo {
	 
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_monitoreo")
	private int idMonitoreo;
	@Column (name = "fecha_inicio")
	private Date fechaInicio;
	@Column (name = "fecha_fin")
	private Date fechaFin;
	@Column (name = "duracion")
	private Float duracion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_aplicacion") 
	@JsonBackReference
	private Aplicacion aplicacion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_accion")
	@JsonBackReference
	private Accion accion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_evento")
	@JsonBackReference
	private Evento evento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	@JsonBackReference
	private User user;
	public int getIdMonitoreo() {
		return idMonitoreo;
	}
	public void setIdMonitoreo(int idMonitoreo) {
		this.idMonitoreo = idMonitoreo;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Float getDuracion() {
		return duracion;
	}
	public void setDuracion(Float duracion) {
		this.duracion = duracion;
	}
	public Aplicacion getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	public Accion getAccion() {
		return accion;
	}
	public void setAccion(Accion accion) {
		this.accion = accion;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Monitoreo(int idMonitoreo, Date fechaInicio, Date fechaFin, Float duracion, Aplicacion aplicacion,
			Accion accion, Evento evento, User user) {
		super();
		this.idMonitoreo = idMonitoreo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracion = duracion;
		this.aplicacion = aplicacion;
		this.accion = accion;
		this.evento = evento;
		this.user = user;
	}
	public Monitoreo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
