package com.empresa.api.dominio.service;

import java.util.List;

import com.empresa.api.dominio.dto.RespuestaDto;

public interface CRUD<T> {

	List<T> obtenerTodo();
	RespuestaDto obtener(long key);
	boolean insertar(T modelo);
	boolean actualizar(T modelo);
}
