package com.empresa.api.controller;

import java.util.List;

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

import com.empresa.api.dominio.dto.PedidoClienteDto;
import com.empresa.api.dominio.dto.RespuestaDto;
import com.empresa.api.dominio.dto.UsuarioDto;
import com.empresa.api.dominio.model.Usuario;
import com.empresa.api.dominio.service.IUsuarioService;
import com.empresa.api.dominio.utilitarios.Constante;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/lista-usuarios")
	public ResponseEntity<List<Usuario>> usuarios() { 
		List<Usuario> usuariosAlmacenados = usuarioService.obtenerTodo();
		return new ResponseEntity<>(usuariosAlmacenados, HttpStatus.OK);
	}

	@PostMapping("/add-usuario")
	public ResponseEntity<RespuestaDto> addusuario(@RequestBody UsuarioDto info) {
		RespuestaDto respuesta = new RespuestaDto();
		Usuario usuario = new Usuario(info.getNombre(), info.getApellido(), info.getCedula(), info.getTipoUsuario(),
				info.getCorreo(), info.getPassword());
		boolean usuarioRespuesta = usuarioService.insertar(usuario);
		if(usuarioRespuesta) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA); 
			return  ResponseEntity.ok(respuesta);
		}
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		return ResponseEntity.ok(respuesta);
	}
	
	@PutMapping("/cambiar-info-usuario")
	public  ResponseEntity<RespuestaDto> actualizarUsuario(@RequestBody  UsuarioDto info){
		RespuestaDto respuesta = new RespuestaDto();
		Usuario usuario = new Usuario(info.getNombre(), info.getApellido(), info.getCedula(), info.getTipoUsuario(),
				info.getCorreo(), info.getPassword());
		boolean usuarioRespuesta = usuarioService.actualizar(usuario);
		if(usuarioRespuesta) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA); 
			return  ResponseEntity.ok(respuesta);
		}
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		return ResponseEntity.ok(respuesta);
	}
	
	@GetMapping("/obtener-usuario/{id}")
	public ResponseEntity<RespuestaDto>  obtenerUsuarioPorId(@PathVariable(value ="id") long idUsuario){
		RespuestaDto usuarioEncontrado = usuarioService.obtener(idUsuario);
		
		if(usuarioEncontrado.getCodigo() != 200) {
			return new ResponseEntity<>(usuarioEncontrado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
	}
	
	 
	
	@GetMapping("/pedidos-usuario/{id}")
	public ResponseEntity<PedidoClienteDto> pedidosUsuario(@PathVariable(value = "id") long id){
	 
		PedidoClienteDto pedidosCliente = usuarioService.pedidosPorCliente(id);
		
		return new ResponseEntity<>(pedidosCliente,HttpStatus.OK);
	}
}
