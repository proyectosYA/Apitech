package com.empresa.api.dominio.service;

import com.empresa.api.dominio.model.Producto;

public interface IProductoService extends CRUD<Producto> {

	Producto disminuirStock(String nombre, int cantidad);
}
