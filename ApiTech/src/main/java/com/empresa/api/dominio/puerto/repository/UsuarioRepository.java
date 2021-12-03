package com.empresa.api.dominio.puerto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empresa.api.dominio.model.Pedido;
import com.empresa.api.dominio.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.correo =:correo")
	Usuario verificarUsuarioExistente(@Param (value = "correo") String correo);
	
	@Query("select p from Pedido p where p.estado = :estado")
	List<Pedido> pedidos(@Param (value = "estado") String estado);
 
	@Query("select p from Pedido p where p.usuario.id =:id")
	List<Pedido> pedidosPorUsuario(@Param (value ="id") long id);
}
