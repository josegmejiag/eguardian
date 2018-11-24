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
@Table(name ="asistentes")

@NamedNativeQuery(
        name = "deleteAsistentesByEvento",
        query = "DELETE FROM prueba.asistentes WHERE id_evento = :idEvento"
    )
public class Asistentes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_asistente")
	private int idAsistente;
	@Column (name = "numero_telefono")
	private String numeroTelefono;
	@Column (name = "IMEI")
	private String Imei;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_evento")
	@JsonBackReference
	private Evento evento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	@JsonBackReference
	private User usuario;
	
	
	public int getIdAsistente() {
		return idAsistente;
	}
	public void setIdAsistente(int idAsistente) {
		this.idAsistente = idAsistente;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getImei() {
		return Imei;
	}
	public void setImei(String imei) {
		Imei = imei;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public Asistentes() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Asistentes [idAsistente=" + idAsistente + ", numeroTelefono=" + numeroTelefono + ", Imei=" + Imei
				+ ", evento=" + evento + ", usuario=" + usuario + "]";
	}
	public Asistentes(int idAsistente, String numeroTelefono, String imei, Evento evento, User usuario) {
		super();
		this.idAsistente = idAsistente;
		this.numeroTelefono = numeroTelefono;
		Imei = imei;
		this.evento = evento;
		this.usuario = usuario;
	}
	
	
	
	
	

}
