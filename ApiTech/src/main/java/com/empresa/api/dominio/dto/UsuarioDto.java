package com.empresa.api.dominio.dto;

import java.io.Serializable;

public class UsuarioDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;

	private String apellido;

	private long cedula;

	private char tipoUsuario;

	private String correo;

	private String password;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public char getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
