package com.empresa.api.dominio.dto;

import java.io.Serializable;

public class ProductoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String nombre;

	private double precio;

	private String descripcion;

	private char estadoProducto;

	private int stock;
	
	private int cantidadProducto;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public char getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(char estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	 

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	 

}
