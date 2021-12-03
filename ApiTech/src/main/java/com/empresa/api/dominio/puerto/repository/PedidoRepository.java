package com.empresa.api.dominio.puerto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empresa.api.dominio.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("select p from Pedido p where p.estado =:estado")
	List<Pedido> pedidos(@Param (value = "estado")String estado);
	
//	@Query("select p from Pedido p where p.fk_usuario =:id")
//	List<Pedido> pedidosPorUsuario(@Param (value ="id") long id);
	
	@Query(value="select * from Pedido p inner join Usuario u on(p.fk_usuario = u.id) where u.id =:id", nativeQuery=true)
	List<Pedido> pedidosPorUsuario(@Param (value ="id") long id);
}
