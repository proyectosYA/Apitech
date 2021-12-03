package com.empresa.api.dominio.dto;

import java.io.Serializable;
import java.util.List;

import com.empresa.api.dominio.model.Pedido;

public class PedidoAdminDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numeroPedidos;
	private List<Pedido> pedidos;

	public int getNumeroPedidos() {
		return numeroPedidos;
	}

	public void setNumeroPedidos(int numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
