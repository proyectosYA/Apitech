package com.empresa.api.dominio.service;

import com.empresa.api.dominio.dto.PedidoClienteDto;
import com.empresa.api.dominio.dto.RespuestaTokenDto;
import com.empresa.api.dominio.model.Usuario;

public interface IUsuarioService extends CRUD<Usuario> {

	RespuestaTokenDto login(String usuario, String pass); 

	PedidoClienteDto pedidosPorCliente(long idUsuario);
}
