package com.bugcatcorp.app_bugcat_store.repository;

import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombres(String nombre);
    Optional<Producto>findByNombre(String nombre);
    Optional<Producto>findByDescripcion(String descripcion);
    Optional<Producto>findByPrecio(Double precio);
}

