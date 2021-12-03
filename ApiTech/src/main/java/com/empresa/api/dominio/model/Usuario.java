package com.empresa.api.dominio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;

	private String apellido;

	private long cedula;

	private char tipoUsuario;

	private String correo;

	private String password;
 
	public Usuario() {}
	
	

	public Usuario(long id,String nombre, String apellido, long cedula, char tipoUsuario, String correo) {
		super(); 
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.tipoUsuario = tipoUsuario;
		this.correo = correo;
	}



	public Usuario(String nombre, String apellido, long cedula, char tipoUsuario, String correo, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.tipoUsuario = tipoUsuario;
		this.correo = correo;
		this.password = password;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public char getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioSecurity [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", tipoUsuario=" + tipoUsuario + ", correo=" + correo + ", password=" + password + ", pedido=";
	}

}
