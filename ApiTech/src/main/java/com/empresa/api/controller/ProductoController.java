package com.empresa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.api.dominio.dto.ProductoDto;
import com.empresa.api.dominio.dto.RespuestaDto;
import com.empresa.api.dominio.model.Producto;
import com.empresa.api.dominio.service.IProductoService;
import com.empresa.api.dominio.utilitarios.Constante;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@GetMapping("productos")
	public ResponseEntity<List<Producto>> productos() {
		List<Producto> productos = productoService.obtenerTodo();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	@RequestMapping("add-producto")
	public ResponseEntity<RespuestaDto> addProducto(@RequestBody ProductoDto info) {
		Producto producto = new Producto(info.getNombre(), info.getPrecio(),
				info.getDescripcion(), info.getEstadoProducto(), info.getStock(), info.getCantidadProducto());
		
		RespuestaDto respuesta = new RespuestaDto();
		boolean productoAlmacenado = productoService.insertar(producto);
		if (productoAlmacenado) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA); 
			return ResponseEntity.ok(respuesta);
		}

		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		
		return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("cambiar-info-producto")
	public  ResponseEntity<RespuestaDto> actualizarProducto(@RequestBody  ProductoDto info){
		Producto producto = new Producto(info.getNombre(), info.getPrecio(),
				info.getDescripcion(), info.getEstadoProducto(), info.getStock(), info.getCantidadProducto());
		RespuestaDto respuesta = new RespuestaDto();
		boolean productoAlmacenado = productoService.actualizar(producto);
		if (productoAlmacenado) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA);
			respuesta.setModelo(producto);
			return ResponseEntity.ok(respuesta);
		}

		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		
		return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("producto/{id}")
	public ResponseEntity<RespuestaDto>  obtenerProductoPorId(@PathVariable(value ="id") long idProducto){
		RespuestaDto usuarioEncontrado = productoService.obtener(idProducto);
		
		if(usuarioEncontrado.getCodigo() != 200) {
			return new ResponseEntity<>(usuarioEncontrado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
	}
}
