package com.empresa.api.dominio.dto;

import java.io.Serializable;

import com.empresa.api.dominio.model.Producto;

public class PedidoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	  
	private long idUsuario;
	  
	private Producto[] productos;
 
	
	 
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	} 

	 
	
	
	
}
