package com.empresa.api.dominio.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;

	private double precio;

	private String descripcion;

	private char estadoProducto;

	private int stock;
	
	private int cantidadProducto;
	@JsonBackReference
	@ManyToMany(mappedBy="productos")
	private Set<Pedido> pedidos = new HashSet<>();

	
	public Producto() {}

	

	public Producto(String nombre, double precio, String descripcion, char estadoProducto, int stock,
			int cantidadProducto) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.estadoProducto = estadoProducto;
		this.stock = stock;
		this.cantidadProducto = cantidadProducto;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


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


	public Set<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	 
	
}
