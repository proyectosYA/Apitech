package com.empresa.api.dominio.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.api.dominio.dto.PedidoDto;
import com.empresa.api.dominio.dto.PedidoEstadoDto;
import com.empresa.api.dominio.dto.RespuestaDto;
import com.empresa.api.dominio.model.Pedido;
import com.empresa.api.dominio.model.Producto;
import com.empresa.api.dominio.model.Usuario;
import com.empresa.api.dominio.puerto.repository.PedidoRepository;
import com.empresa.api.dominio.utilitarios.Constante;

@Service
public class ServicePedido implements IPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private IUsuarioService usuarioService;
	
	 

	@Override
	public List<Pedido> obtenerTodo() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos;
	}

	@Override
	public RespuestaDto obtener(long key) {
		RespuestaDto respuesta = new RespuestaDto();
		Pedido pedido = pedidoRepository.findById(key).get();
		if (pedido != null) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA);
			respuesta.setModelo(pedido);
			return respuesta;
		}
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		respuesta.setModelo(pedido);
		return respuesta;
	}

	@Override
	public boolean insertar(Pedido modelo) {
		LocalDate fechaPedido = modelo.getFechaPedido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaEntrega = fechaPedido.plusDays(4);
		modelo.setFechaentrega(Date.from(fechaEntrega.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Pedido pedido = pedidoRepository.save(modelo);
		return pedido != null ? true : false;
	}

	@Override
	public boolean actualizar(Pedido modelo) {
		Pedido pedido = pedidoRepository.save(modelo);
		return pedido != null ? true : false;
	}

	@Override
	public PedidoEstadoDto obtenerPedidosPorEstado() {
		PedidoEstadoDto pedido = new PedidoEstadoDto();
		pedido.setAutorizado(pedidoRepository.pedidos("autorizado").size());
		pedido.setRecibido(pedidoRepository.pedidos("recibido").size());
		return pedido;
	}

	@Override
	public List<Pedido> pedidosPorCliente(long idUsuario) {
		List<Pedido> pedidosUsuario = new ArrayList<>();
		 
		pedidoRepository.pedidosPorUsuario(idUsuario).forEach(pedido->{
			 pedidosUsuario.add(new Pedido(pedido.getNumeroPedido(), 
					 pedido.getFechaPedido(), pedido.getFechaentrega(), pedido.getTotalProductos(), pedido.getEstado(), pedido.getProductos()));
		 });
		return pedidosUsuario;
	}

	@Override
	public synchronized boolean recepcionPedido(PedidoDto info) {

		Set<Producto> productos = new HashSet<>();
		int incremento = 1;
		int numero = 000000;
		long numeroFactura = numero + incremento;
		incremento++;

		double totalProductos = 0;
		String estado = "autorizado";
		 

		for (Producto producto : Arrays.asList(info.getProductos())) {
			totalProductos += (producto.getPrecio() * producto.getCantidadProducto());
			
			productos.add(new Producto(producto.getNombre(), producto.getPrecio(), producto.getDescripcion(),
					producto.getEstadoProducto(), producto.getStock(), producto.getCantidadProducto()));
	 
		}

		RespuestaDto usuario = usuarioService.obtener(info.getIdUsuario());
		if (usuario.getCodigo() == 200) {
			Pedido pedido = new Pedido(numeroFactura, new Date(), totalProductos, estado, productos,
					(Usuario) usuario.getModelo());
			
			boolean pedidoAgregado = insertar(pedido);
			
			
			return pedidoAgregado;
		}

		return false;
	}

}
