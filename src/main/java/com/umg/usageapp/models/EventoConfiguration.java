package com.umg.usageapp.models;

import java.util.List;



public class EventoConfiguration {

	private int idEventoConfiguration; 
	private int evento;
	private List<Aplicacion> aplicacion;
	private List<Accion>  accion;
	
	
	
	public int getIdEventoConfiguration() {
		return idEventoConfiguration;
	}
	public void setIdEventoConfiguration(int idEventoConfiguration) {
		this.idEventoConfiguration = idEventoConfiguration;
	}
	public int getEvento() {
		return evento;
	}
	public void setEvento(int evento) {
		this.evento = evento;
	}
	public List<Aplicacion> getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(List<Aplicacion> aplicacion) {
		this.aplicacion = aplicacion;
	}
	public List<Accion> getAccion() {
		return accion;
	}
	public void setAccion(List<Accion> accion) {
		this.accion = accion;
	}
	
}
