package com.umg.usageapp.models;

import java.util.Date;

public class MonitoreoResult {
	
	
	private String  Nombre_Empresa;
	private String Nombre_Evento;
	private String Usuario_Genero;
	private String Nombre_Usuario;
	private String Apellido_Usuario;
	private String Nombre_Aplicacion;
	private String Notificacion_Utilizada;
	private Date fecha_Fin;
	private Date fecha_Inicio;
	private Float Duracion;
	public String getNombre_Empresa() {
		return Nombre_Empresa;
	}
	public void setNombre_Empresa(String nombre_Empresa) {
		Nombre_Empresa = nombre_Empresa;
	}
	public String getNombre_Evento() {
		return Nombre_Evento;
	}
	public void setNombre_Evento(String nombre_Evento) {
		Nombre_Evento = nombre_Evento;
	}
	public String getUsuario_Genero() {
		return Usuario_Genero;
	}
	public void setUsuario_Genero(String usuario_Genero) {
		Usuario_Genero = usuario_Genero;
	}
	public String getNombre_Usuario() {
		return Nombre_Usuario;
	}
	public void setNombre_Usuario(String nombre_Usuario) {
		Nombre_Usuario = nombre_Usuario;
	}
	public String getApellido_Usuario() {
		return Apellido_Usuario;
	}
	public void setApellido_Usuario(String apellido_Usuario) {
		Apellido_Usuario = apellido_Usuario;
	}
	public String getNombre_Aplicacion() {
		return Nombre_Aplicacion;
	}
	public void setNombre_Aplicacion(String nombre_Aplicacion) {
		Nombre_Aplicacion = nombre_Aplicacion;
	}
	public String getNotificacion_Utilizada() {
		return Notificacion_Utilizada;
	}
	public void setNotificacion_Utilizada(String notificacion_Utilizada) {
		Notificacion_Utilizada = notificacion_Utilizada;
	}
	public Date getFecha_Fin() {
		return fecha_Fin;
	}
	public void setFecha_Fin(Date fecha_Fin) {
		this.fecha_Fin = fecha_Fin;
	}
	public Date getFecha_Inicio() {
		return fecha_Inicio;
	}
	public void setFecha_Inicio(Date fecha_Inicio) {
		this.fecha_Inicio = fecha_Inicio;
	}
	public Float getDuracion() {
		return Duracion;
	}
	public void setDuracion(Float duracion) {
		Duracion = duracion;
	}
	public MonitoreoResult(String nombre_Empresa, String nombre_Evento, String usuario_Genero, String nombre_Usuario,
			String apellido_Usuario, String nombre_Aplicacion, String notificacion_Utilizada, Date fecha_Fin,
			Date fecha_Inicio, Float duracion) {
		super();
		Nombre_Empresa = nombre_Empresa;
		Nombre_Evento = nombre_Evento;
		Usuario_Genero = usuario_Genero;
		Nombre_Usuario = nombre_Usuario;
		Apellido_Usuario = apellido_Usuario;
		Nombre_Aplicacion = nombre_Aplicacion;
		Notificacion_Utilizada = notificacion_Utilizada;
		this.fecha_Fin = fecha_Fin;
		this.fecha_Inicio = fecha_Inicio;
		Duracion = duracion;
	}
	public MonitoreoResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MonitoreoResult [Nombre_Empresa=" + Nombre_Empresa + ", Nombre_Evento=" + Nombre_Evento
				+ ", Usuario_Genero=" + Usuario_Genero + ", Nombre_Usuario=" + Nombre_Usuario + ", Apellido_Usuario="
				+ Apellido_Usuario + ", Nombre_Aplicacion=" + Nombre_Aplicacion + ", Notificacion_Utilizada="
				+ Notificacion_Utilizada + ", fecha_Fin=" + fecha_Fin + ", fecha_Inicio=" + fecha_Inicio + ", Duracion="
				+ Duracion + "]";
	}
	
	
	
	
	
	
	

}
