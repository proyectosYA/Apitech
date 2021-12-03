package com.empresa.api.dominio.dto;

import java.io.Serializable;

public class RespuestaTokenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensaje;
	private int codigo;
	private String token;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
