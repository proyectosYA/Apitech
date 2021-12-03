package com.empresa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.api.dominio.dto.PedidoAdminDto;
import com.empresa.api.dominio.dto.PedidoDto;
import com.empresa.api.dominio.dto.PedidoEstadoDto;
import com.empresa.api.dominio.dto.RespuestaDto;
import com.empresa.api.dominio.model.Pedido;
import com.empresa.api.dominio.service.IPedidoService;
import com.empresa.api.dominio.utilitarios.Constante;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;
	
	
	@PostMapping("add-pedido")
	public ResponseEntity<RespuestaDto> addPedido(@RequestBody PedidoDto info){
		RespuestaDto respuesta = new RespuestaDto();
		  
		boolean pedidoAlmacenado =  pedidoService.recepcionPedido(info); 
		
		if(pedidoAlmacenado) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		return new ResponseEntity<> (respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("cambiar-info-pedido")
	public ResponseEntity<RespuestaDto> actualizarPedido(@RequestBody PedidoDto info){
		RespuestaDto respuesta = new RespuestaDto(); 
		Pedido pedido = new Pedido();
		boolean pedidoAgregado = pedidoService.actualizar(pedido);
		if(pedidoAgregado) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		return new ResponseEntity<> (respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("tipo-pedidos-admin")
	public ResponseEntity<PedidoEstadoDto> pedidos(){
		PedidoEstadoDto pedidos = pedidoService.obtenerPedidosPorEstado();
		return new ResponseEntity<>(pedidos, HttpStatus.OK);
	}
	
	@GetMapping("pedidos-admin/{id}")
	public ResponseEntity<PedidoAdminDto> pedidosUsuario(@PathVariable(value ="id") long id){
		PedidoAdminDto pedidosUsuario = new PedidoAdminDto();
		
		pedidosUsuario.setNumeroPedidos(pedidoService.pedidosPorCliente(id).size());
		pedidosUsuario.setPedidos(pedidoService.pedidosPorCliente(id));
		
		return new ResponseEntity<>(pedidosUsuario,HttpStatus.OK);
	}
}
