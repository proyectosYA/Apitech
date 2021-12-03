package com.empresa.api.dominio.dto;

import java.io.Serializable;

public class LoginDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 835725223379127860L;

	private String correo;
	private String password;

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
