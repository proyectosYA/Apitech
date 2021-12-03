package com.empresa.api.dominio.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.empresa.api.dominio.dto.PedidoClienteDto;
import com.empresa.api.dominio.dto.PedidoEstadoDto;
import com.empresa.api.dominio.dto.RespuestaDto;
import com.empresa.api.dominio.dto.RespuestaTokenDto;
import com.empresa.api.dominio.model.Pedido;
import com.empresa.api.dominio.model.Usuario;
import com.empresa.api.dominio.puerto.repository.UsuarioRepository;
import com.empresa.api.dominio.utilitarios.Constante;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ServiceUsuario implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Environment env;

	@Override
	public List<Usuario> obtenerTodo() {
		List<Usuario> usuarios = new ArrayList<>();
		List<Usuario> usuariosAlmacenados = usuarioRepository.findAll();
		if (!usuariosAlmacenados.isEmpty()) {
			usuariosAlmacenados.forEach(action -> {
				usuarios.add(new Usuario(action.getId(), action.getNombre(), action.getApellido(), action.getCedula(),
						action.getTipoUsuario(), action.getCorreo()));
			});
			return usuarios;
		}
		return usuarios;
	}

	@Override
	public RespuestaDto obtener(long key) {
		RespuestaDto respuesta = new RespuestaDto();
		Usuario usuarioPorId = usuarioRepository.findById(key).get();
		if (usuarioPorId != null) {
			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA);
			respuesta.setModelo(usuarioPorId);
			return respuesta;
		}
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.PETICION_ERRONEA);
		return respuesta;
	}

	@Override
	public boolean insertar(Usuario modelo) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, modelo.getPassword());
		modelo.setPassword(hash);
		Usuario usuarioExistente = usuarioRepository.verificarUsuarioExistente(modelo.getCorreo());
		if (usuarioExistente != null) {
			return false;
		}
		Usuario usuario = usuarioRepository.save(modelo);
		return usuario != null ? true : false;
	}

	@Override
	public boolean actualizar(Usuario modelo) {
		Usuario usuario = usuarioRepository.save(modelo);

		return usuario != null ? true : false;
	}

	@Override
	public RespuestaTokenDto login(String usuario, String pass) {
		RespuestaTokenDto respuesta = new RespuestaTokenDto();
		// verificar usuario
		Usuario usuarioVerificado = verificarUsuario(usuario, pass);

		if (usuarioVerificado != null) {

			String claveSecreta = env.getProperty("key.token");
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER, ROLE_ADMIN");

			String token = Jwts.builder().setId("ApiJWTrest").setSubject(usuarioVerificado.getCorreo())
					.claim("authorities",
							grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 600000))
					.signWith(SignatureAlgorithm.HS512, claveSecreta.getBytes()).compact();
			String tokenConfigurado = "Bearer " + token;

			respuesta.setCodigo(200);
			respuesta.setMensaje(Constante.PETICION_EXITOSA);
			respuesta.setToken(tokenConfigurado);
			return respuesta;
		}
		respuesta.setCodigo(500);
		respuesta.setMensaje(Constante.CAMPOS_ERRADOS);
		return respuesta;
	}

	private Usuario verificarUsuario(String usuario, String pass) {

		Usuario usuarioExistente = usuarioRepository.verificarUsuarioExistente(usuario);
		if (usuarioExistente != null) {
			String hashPassword = usuarioExistente.getPassword();
			Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

			if (argon.verify(hashPassword, pass)) {
				return usuarioExistente;
			}
		}
		return null;
	}

	@Override
	public PedidoClienteDto pedidosPorCliente(long idUsuario) {
		PedidoClienteDto respuesta = new PedidoClienteDto();
		PedidoEstadoDto pedido = new PedidoEstadoDto();
		pedido.setAutorizado(usuarioRepository.pedidos("autorizado").size());
		pedido.setRecibido(usuarioRepository.pedidos("recibido").size());
		Usuario usuarioAlmacenado = usuarioRepository.findById(idUsuario).get();
		if (usuarioAlmacenado != null) {
			List<Pedido> pedidos = new ArrayList<>();
					
		usuarioRepository.pedidosPorUsuario(usuarioAlmacenado.getId()).forEach(pedidoAlmacenado-> {
			pedidos.add(new Pedido(pedidoAlmacenado.getNumeroPedido(), pedidoAlmacenado.getFechaPedido(),
					pedidoAlmacenado.getFechaentrega(), pedidoAlmacenado.getTotalProductos(), pedidoAlmacenado.getEstado(), 
					pedidoAlmacenado.getProductos()));
			 }); 
			respuesta.setAutorizado(pedido.getAutorizado());
			respuesta.setRecibido(pedido.getRecibido());
			respuesta.setPedidos(pedidos);
			return respuesta;
		}
		respuesta.setAutorizado(0);
		respuesta.setRecibido(0);
		return respuesta;
	}

}
