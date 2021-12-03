package com.empresa.api.dominio.puerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.api.dominio.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
