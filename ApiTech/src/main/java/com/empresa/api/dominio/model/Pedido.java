package com.empresa.api.dominio.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedido")
public class Pedido {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long numeroPedido;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPedido;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaentrega;

	private double totalProductos;

	private String estado;
 

	@JoinTable(name = "producto_pedido", joinColumns = { @JoinColumn(name = "fk_pedido",nullable=false)},
			inverseJoinColumns = { @JoinColumn(name = "fk_producto", nullable=false) })
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Producto> productos = new HashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario", referencedColumnName = "id")
	private Usuario usuario;
	
	public Pedido() {}
	 
	
	  
	public Pedido(long numeroPedido, Date fechaPedido, Date fechaentrega, double totalProductos, String estado,
			Set<Producto> productos) {
		super();
		this.numeroPedido = numeroPedido;
		this.fechaPedido = fechaPedido;
		this.fechaentrega = fechaentrega;
		this.totalProductos = totalProductos;
		this.estado = estado;
		this.productos = productos;
	}



	public Pedido(long numeroPedido, Date fechaPedido, double totalProductos, String estado,
			Set<Producto> productos, Usuario usuario) {
		super();
		this.numeroPedido = numeroPedido;
		this.fechaPedido = fechaPedido;
		this.totalProductos = totalProductos;
		this.estado = estado;  
		this.productos = productos;
		this.usuario = usuario;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getNumeroPedido() {
		return numeroPedido;
	}


	public void setNumeroPedido(long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}


	public Date getFechaPedido() {
		return fechaPedido;
	}


	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}


	public Date getFechaentrega() {
		return fechaentrega;
	}


	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}


	public double getTotalProductos() {
		return totalProductos;
	}


	public void setTotalProductos(double totalProductos) {
		this.totalProductos = totalProductos;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Set<Producto> getProductos() {
		return productos;
	}


	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
 
	
}
