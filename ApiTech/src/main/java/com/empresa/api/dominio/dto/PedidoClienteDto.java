package com.empresa.api.dominio.dto;

import java.io.Serializable;
import java.util.List;

import com.empresa.api.dominio.model.Pedido;

public class PedidoClienteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int autorizado;
	private int recibido;
	private List<Pedido> pedidos;

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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
