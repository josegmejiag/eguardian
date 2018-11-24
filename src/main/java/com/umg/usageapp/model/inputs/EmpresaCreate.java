package com.umg.usageapp.model.inputs;

import java.util.List;

public class EmpresaCreate {
	

	private String nombreEmpresa;
	private String direccionEmpresa;
	private int numeroColaboradoresEmpresa;
	private String telefonoEmpresa;
	private String logo;
	private String descripcionEmpresa;
	private List<Integer> idNegocios;
	private String nombreUsuario;
	private String apellidosUsuario;
	private String telefono;
	private String contrasenia;
	private int puesto;
	private String email;
	
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}
	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}
	public int getNumeroColaboradoresEmpresa() {
		return numeroColaboradoresEmpresa;
	}
	public void setNumeroColaboradoresEmpresa(int numeroColaboradoresEmpresa) {
		this.numeroColaboradoresEmpresa = numeroColaboradoresEmpresa;
	}
	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}
	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDescripcionEmpresa() {
		return descripcionEmpresa;
	}
	public void setDescripcionEmpresa(String descripcionEmpresa) {
		this.descripcionEmpresa = descripcionEmpresa;
	}
	public List<Integer> getIdNegocios() {
		return idNegocios;
	}
	public void setIdNegocios(List<Integer> idNegocios) {
		this.idNegocios = idNegocios;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidosUsuario() {
		return apellidosUsuario;
	}
	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPuesto() {
		return puesto;
	}
	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


}
