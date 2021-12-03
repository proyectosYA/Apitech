package com.empresa.api.dominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.api.dominio.dto.RespuestaDto;
import com.empresa.api.dominio.model.Producto;
import com.empresa.api.dominio.puerto.repository.ProductoRepository;
import com.empresa.api.dominio.utilitarios.Constante;

@Service
public class ServiceProducto implements IProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Producto> obtenerTodo() {
		List<Producto > productos = productoRepository.findAll();
		return productos;
	}

	@Override
	public RespuestaDto obtener(long key) {
		RespuestaDto respuesta = new RespuestaDto();
		Producto producto = productoRepository.findById(key).get();
		if (producto != null) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA);
			respuesta.setModelo(producto);
			return respuesta;
		}
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		respuesta.setModelo(producto);
		return respuesta;
	}

	@Override
	public boolean insertar(Producto modelo) {
		Producto producto = productoRepository.save(modelo);

		return producto != null ? true : false;
	}

	@Override
	public boolean actualizar(Producto modelo) {
		Producto producto = productoRepository.save(modelo);
		return producto != null ? true : false;
	}

	@Override
	public Producto disminuirStock(String nombre, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

}
