package com.umg.usageapp.model.inputs;

import java.util.List;

import javax.persistence.Entity;

import com.umg.usageapp.models.Accion;
import com.umg.usageapp.models.Aplicacion;
import com.umg.usageapp.models.Evento;
import com.umg.usageapp.models.User;

public class UpdateEvento {

    public Evento evento ;
    public List<User> usuarios;
    public List<Aplicacion> aplicaciones;
    public List<Accion> acciones;
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public List<User> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}
	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}
	public List<Accion> getAcciones() {
		return acciones;
	}
	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	public UpdateEvento(Evento evento, List<User> usuarios, List<Aplicacion> aplicaciones, List<Accion> acciones) {
		super();
		this.evento = evento;
		this.usuarios = usuarios;
		this.aplicaciones = aplicaciones;
		this.acciones = acciones;
	}
	public UpdateEvento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
    
	
}
