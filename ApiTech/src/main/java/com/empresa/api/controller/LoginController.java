package com.empresa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.api.dominio.dto.LoginDto;
import com.empresa.api.dominio.dto.RespuestaLoginDto;
import com.empresa.api.dominio.dto.RespuestaTokenDto;
import com.empresa.api.dominio.service.IUsuarioService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class LoginController {
	
	
	@Autowired
	private  IUsuarioService serviceUsuario;
	
	@PostMapping("login")
	public ResponseEntity<RespuestaLoginDto> loginUsuario(@RequestBody LoginDto info){ 
		RespuestaLoginDto respuesta = new RespuestaLoginDto();
		RespuestaTokenDto respuestaToken = serviceUsuario.login(info.getCorreo(), info.getPassword());
		
		if(respuestaToken.getCodigo() != 200) {
			respuesta.setCodigo(500);
			respuesta.setToken(null); 
			return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.setCodigo(respuestaToken.getCodigo());
		respuesta.setToken(respuestaToken.getToken());
		
		return new ResponseEntity<>(respuesta,HttpStatus.OK);
	}

}
