package com.umg.usageapp.models;

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
@Table(name ="accion_evento")

@NamedNativeQuery(
        name = "deleteAccionByEvento",
        query = "DELETE FROM prueba.accion_evento WHERE id_evento = :idEvento"
    )



public class AccionEvento {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_accion_evento")
	private int idAccionEvento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_accion")
	@JsonBackReference
	private Accion accion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_evento")
	@JsonBackReference
	private Evento evento;
	
	
	public int getIdAccionEvento() {
		return idAccionEvento;
	}
	public void setIdAccionEvento(int idAccionEvento) {
		this.idAccionEvento = idAccionEvento;
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
	
	public AccionEvento(int idAccionEvento, Accion accion, Evento evento) {
		super();
		this.idAccionEvento = idAccionEvento;
		this.accion = accion;
		this.evento = evento;
	}
	public AccionEvento() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	
	
	
	

}
