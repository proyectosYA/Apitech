package com.empresa.api.dominio.dto;

import java.io.Serializable;

public class PedidoEstadoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int autorizado;
	private int recibido;

	public int getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(int autorizado) {
		this.autorizado = autorizado;
	}

	public int getRecibido() {
		return recibido;
	}

	public void setRecibido(int recibido) {
		this.recibido = recibido;
	}

}
