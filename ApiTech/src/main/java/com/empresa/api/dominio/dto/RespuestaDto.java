package com.empresa.api.dominio.dto;

import java.io.Serializable;

public class RespuestaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	private int codigo;
	private Object modelo;

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

	public Object getModelo() {
		return modelo;
	}

	public void setModelo(Object modelo) {
		this.modelo = modelo;
	}

}
