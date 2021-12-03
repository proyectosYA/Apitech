package com.empresa.api.dominio.service;

import java.util.List;

import com.empresa.api.dominio.dto.PedidoDto;
import com.empresa.api.dominio.dto.PedidoEstadoDto;
import com.empresa.api.dominio.model.Pedido;

public interface IPedidoService extends CRUD<Pedido> {

	PedidoEstadoDto obtenerPedidosPorEstado();
	
	List<Pedido> pedidosPorCliente(long idUsuario);
	
	 boolean recepcionPedido(PedidoDto info);
	
}
